package com.example.duplication.controller;

import com.example.duplication.api.ScanRequest;
import com.example.duplication.model.DashboardData;
import com.example.duplication.service.SimilarityScanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScanController {
    private final SimilarityScanService service;
    public ScanController(SimilarityScanService service) { this.service = service; }

    @PostMapping("/scan-embed")
    public ResponseEntity<DashboardData> scan(@RequestBody ScanRequest request) {
        return ResponseEntity.ok(service.analyze(request));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() { return ResponseEntity.ok("OK"); }
}
