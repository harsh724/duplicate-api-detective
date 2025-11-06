package com.example.duplication.service;


import com.example.duplication.api.ScanRequest;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TextPrepService {
    public List<List<String>> buildRepoSnippets(List<ScanRequest.Repo> repos) {
        List<List<String>> repoTexts = new ArrayList<>();
        for (ScanRequest.Repo r : repos) {
            List<String> snips = new ArrayList<>();
            if (r.name != null) snips.add("REPO:" + r.name);
            if (r.url  != null) snips.add("URL:" + r.url);
            if (r.files != null) {
                int cap = Math.min(8, r.files.size()); // limit for cost
                for (int i = 0; i < cap; i++) {
                    var f = r.files.get(i);
                    String content = normalize(f.content);
                    if (content.length() > 2000) content = content.substring(0, 2000);
                    snips.add("PATH:" + f.path + "\n" + content);
                }
            }
            if (snips.isEmpty()) snips.add("EMPTY");
            repoTexts.add(snips);
        }
        return repoTexts;
    }

    private static String normalize(String s) {
        if (s == null) return "";
        return s.replaceAll("(?s)/\\*.*?\\*/", " ")
                .replaceAll("(?m)//.*?$", " ")
                .replaceAll("\\s+", " ")
                .replaceAll("[^\\x20-\\x7E]", " ")
                .trim();
    }
}
