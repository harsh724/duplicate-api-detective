package com.example.duplication.model;
import java.util.List;
public class SimilarityPair {
    private String a;
    private String b;
    private int similarity;
    private Integer linesDuplicated;
    private List<String> files;
    public String getA() { return a; }
    public void setA(String v) { a = v; }
    public String getB() { return b; }
    public void setB(String v) { b = v; }
    public int getSimilarity() { return similarity; }
    public void setSimilarity(int v) { similarity = v; }
    public Integer getLinesDuplicated() { return linesDuplicated; }
    public void setLinesDuplicated(Integer v) { linesDuplicated = v; }
    public List<String> getFiles() { return files; }
    public void setFiles(List<String> v) { files = v; }
}
