package com.example.duplication.model;
public class SummaryStats {
    private int services;
    private int duplicateGroups;
    private int highSimilarityPairs;
    private int locSaved;
    private double avgRedundancy;
    public int getServices() { return services; }
    public void setServices(int v) { services = v; }
    public int getDuplicateGroups() { return duplicateGroups; }
    public void setDuplicateGroups(int v) { duplicateGroups = v; }
    public int getHighSimilarityPairs() { return highSimilarityPairs; }
    public void setHighSimilarityPairs(int v) { highSimilarityPairs = v; }
    public int getLocSaved() { return locSaved; }
    public void setLocSaved(int v) { locSaved = v; }
    public double getAvgRedundancy() { return avgRedundancy; }
    public void setAvgRedundancy(double v) { avgRedundancy = v; }
}
