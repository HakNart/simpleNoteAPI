package com.example.simplenoterestapi.controller;


import com.example.simplenoterestapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final UserService userService;
    final JwtEncoder encoder;

    public AuthController(UserService userService, JwtEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }
}
