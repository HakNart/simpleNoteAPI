package com.example.simplenoterestapi.util.validator;

import com.example.simplenoterestapi.dto.RegisterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegisterRequest> {
    @Override
    public boolean isValid(final RegisterRequest registerRequest, ConstraintValidatorContext context) {
        return false;
    }
}
