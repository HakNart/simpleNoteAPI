package com.example.simplenoterestapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<?> testController() {
        return ResponseEntity.ok("Hello, this is successful!");
    }
}
