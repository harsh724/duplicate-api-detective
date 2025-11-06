package com.example.duplication.api;
import java.util.List;

public class ScanRequest {
    public List<Repo> repos;

    public static class Repo {
        public String name;         // "auth"
        public String url;          // optional
        public List<File> files;    // include a few key files/snippets
    }
    public static class File {
        public String path;
        public String content;      // keep short for prototype (1–2 KB per file)
    }
}
