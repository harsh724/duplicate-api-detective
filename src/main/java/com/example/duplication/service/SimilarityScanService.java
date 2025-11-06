package com.example.duplication.service;

import com.example.duplication.api.ScanRequest;
import com.example.duplication.model.*;
import com.example.duplication.util.MathUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimilarityScanService {
    private final EmbeddingService embedding;
    private final TextPrepService textPrep;

    public SimilarityScanService(EmbeddingService embedding, TextPrepService textPrep) {
        this.embedding = embedding; this.textPrep = textPrep;
    }

    public DashboardData analyze(ScanRequest req) {
        if (req == null || req.repos == null || req.repos.isEmpty())
            throw new IllegalArgumentException("No repos provided");

        List<String> names = req.repos.stream()
                .map(r -> r.name == null || r.name.isBlank() ? UUID.randomUUID().toString() : r.name)
                .collect(Collectors.toList());

        // 1) Build snippets and embed
        List<List<String>> repoSnips = textPrep.buildRepoSnippets(req.repos);
        List<String> allSnips = repoSnips.stream().flatMap(List::stream).toList();
        List<float[]> allVecs = embedding.embedTexts(allSnips);

        // 2) Mean-pool per repo
        List<float[]> repoVecs = new ArrayList<>();
        int index = 0;
        for (List<String> snips : repoSnips) {
            List<float[]> subv = allVecs.subList(index, index + snips.size());
            repoVecs.add(MathUtil.meanPool(subv));
            index += snips.size();
        }

        // 3) Similarity matrix (map [-1,1] → [0,100])
        int n = names.size();
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            mat[i][i] = 0;
            for (int j = i+1; j < n; j++) {
                double cos = MathUtil.cosine(repoVecs.get(i), repoVecs.get(j));
                int pct = (int)Math.round(((cos + 1) / 2.0) * 100);
                mat[i][j] = mat[j][i] = Math.max(0, Math.min(100, pct));
            }
        }

        // 4) Top pairs (threshold 30)
        List<SimilarityPair> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) for (int j = i+1; j < n; j++) {
            int s = mat[i][j];
            if (s >= 30) {
                SimilarityPair p = new SimilarityPair();
                p.setA(names.get(i)); p.setB(names.get(j));
                p.setSimilarity(s);
                p.setLinesDuplicated(50 + s * 5); // heuristic for demo
                pairs.add(p);
            }
        }
        pairs.sort((a,b) -> Integer.compare(b.getSimilarity(), a.getSimilarity()));

        // 5) Groups (simple thresholded graph connected components at >=40)
        boolean[] seen = new boolean[n];
        List<DuplicateGroup> groups = new ArrayList<>();
        int gid = 1;
        for (int i = 0; i < n; i++) if (!seen[i]) {
            List<Integer> comp = new ArrayList<>();
            Deque<Integer> dq = new ArrayDeque<>(); dq.add(i); seen[i] = true;
            while (!dq.isEmpty()) {
                int u = dq.removeFirst(); comp.add(u);
                for (int v = 0; v < n; v++) if (!seen[v] && mat[u][v] >= 40) { seen[v] = true; dq.add(v); }
            }
            if (comp.size() > 1) {
                DuplicateGroup g = new DuplicateGroup();
                g.setId("G" + gid++);
                g.setRepos(comp.stream().map(names::get).toList());
                int max = 0, lines = 0;
                for (int a = 0; a < comp.size(); a++)
                    for (int b = a+1; b < comp.size(); b++) {
                        max = Math.max(max, mat[comp.get(a)][comp.get(b)]);
                        lines += 50 + mat[comp.get(a)][comp.get(b)] * 5;
                    }
                g.setMaxSimilarity(max);
                g.setLinesDuplicated(lines);
                groups.add(g);
            }
        }

        // 6) Summary + tiny trend
        SummaryStats sum = new SummaryStats();
        sum.setServices(n);
        sum.setDuplicateGroups(groups.size());
        sum.setHighSimilarityPairs((int)pairs.stream().filter(p -> p.getSimilarity() >= 90).count());
        sum.setLocSaved(pairs.stream().mapToInt(p -> Optional.ofNullable(p.getLinesDuplicated()).orElse(0)).sum());
        double avg = pairs.stream().mapToInt(SimilarityPair::getSimilarity).average().orElse(0.0) * 0.2;
        sum.setAvgRedundancy(Math.round(avg * 10.0) / 10.0);

        List<TrendPoint> trend = new ArrayList<>();
        for (int k = 6; k >= 0; k--) {
            TrendPoint t = new TrendPoint();
            t.setDate(LocalDate.now().minusWeeks(k).toString());
            t.setDuplicationPercent(Math.max(5, sum.getAvgRedundancy() + (k - 3)));
            trend.add(t);
        }

        // 7) Build DashboardData
        DashboardData dd = new DashboardData();
        dd.setSummary(sum);
        dd.setServices(names);
        List<List<Integer>> matrix = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>(n);
            for (int j = 0; j < n; j++) row.add(mat[i][j]);
            matrix.add(row);
        }
        dd.setMatrix(matrix);
        dd.setPairs(pairs);
        dd.setGroups(groups);
        dd.setTrend(trend);
        return dd;
    }
}
