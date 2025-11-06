package com.example.duplication.controller;

import com.example.duplication.model.DashboardData;
import com.example.duplication.service.DuplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DuplicationController {

    private final DuplicationService service;

    public DuplicationController(DuplicationService service) {
        this.service = service;
    }

    @GetMapping("/duplicates")
    public ResponseEntity<DashboardData> getDuplicates() {
        return ResponseEntity.ok(service.getDashboard());
    }
}
