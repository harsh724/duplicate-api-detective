package com.example.duplication.service;

import com.example.duplication.model.DashboardData;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DuplicationService {

    private final DashboardData cache;

    public DuplicationService(ObjectMapper objectMapper) {
        try {
            ClassPathResource resource = new ClassPathResource("mock-data.json");
            this.cache = objectMapper.readValue(resource.getInputStream(), DashboardData.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load mock-data.json", e);
        }
    }

    public DashboardData getDashboard() {
        return cache;
    }
}
