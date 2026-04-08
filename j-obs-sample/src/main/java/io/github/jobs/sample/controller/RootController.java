package io.github.jobs.sample.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class RootController {

    @Value("${spring.application.name:j-obs-sample}")
    private String appName;

    @GetMapping("/")
    public Map<String, Object> root() {
        return Map.of(
                "application", appName,
                "status", "running",
                "timestamp", Instant.now().toString(),
                "endpoints", Map.of(
                        "dashboard", "/j-obs",
                        "api", "/api/orders",
                        "health", "/actuator/health",
                        "prometheus", "/actuator/prometheus"
                )
        );
    }
}
