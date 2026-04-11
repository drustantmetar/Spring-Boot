package com.journalapp.springsecurity.springsecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/health-check")
    public String health() {
        return "Hello World";
    }
}
