package com.example.simplenoterestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedResourceExeception extends RuntimeException {
    private final String resourceName;

    public UnauthorizedResourceExeception(String resourceName) {
        super(String.format("%s not accessible", resourceName));
        this.resourceName = resourceName;

    }
}
