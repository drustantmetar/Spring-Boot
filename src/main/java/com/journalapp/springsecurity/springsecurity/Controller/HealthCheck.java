package com.journalapp.springsecurity.springsecurity.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "HEALTH CHECK API CALL")
public class HealthCheck {
    @Operation(summary = "Used to check the Health of the application")
    @GetMapping("/health-check")
    public String health() {
        return "Hello World";
    }
}
