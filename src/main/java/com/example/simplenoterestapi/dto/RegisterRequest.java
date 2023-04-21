package com.example.simplenoterestapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @Size(min=6, message = "Minimum 6 chars required")
    private String password;

}
