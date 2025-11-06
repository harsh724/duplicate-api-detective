package com.example.duplication.model;
import java.util.List;
public class DuplicateGroup {
    private String id;
    private List<String> repos;
    private int maxSimilarity;
    private int linesDuplicated;
    public String getId() { return id; }
    public void setId(String v) { id = v; }
    public List<String> getRepos() { return repos; }
    public void setRepos(List<String> v) { repos = v; }
    public int getMaxSimilarity() { return maxSimilarity; }
    public void setMaxSimilarity(int v) { maxSimilarity = v; }
    public int getLinesDuplicated() { return linesDuplicated; }
    public void setLinesDuplicated(int v) { linesDuplicated = v; }
}
