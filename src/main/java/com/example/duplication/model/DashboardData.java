package com.example.duplication.model;
import java.util.List;
public class DashboardData {
    private SummaryStats summary;
    private List<String> services;
    private List<List<Integer>> matrix;
    private List<SimilarityPair> pairs;
    private List<DuplicateGroup> groups;
    private List<TrendPoint> trend;

    public SummaryStats getSummary() { return summary; }
    public void setSummary(SummaryStats v) { summary = v; }
    public List<String> getServices() { return services; }
    public void setServices(List<String> v) { services = v; }
    public List<List<Integer>> getMatrix() { return matrix; }
    public void setMatrix(List<List<Integer>> v) { matrix = v; }
    public List<SimilarityPair> getPairs() { return pairs; }
    public void setPairs(List<SimilarityPair> v) { pairs = v; }
    public List<DuplicateGroup> getGroups() { return groups; }
    public void setGroups(List<DuplicateGroup> v) { groups = v; }
    public List<TrendPoint> getTrend() { return trend; }
    public void setTrend(List<TrendPoint> v) { trend = v; }
}

