package com.example.simplenoterestapi.util.validator;

import com.example.simplenoterestapi.dto.SignupRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignupRequest> {
    @Override
    public boolean isValid(final SignupRequest signupRequest, ConstraintValidatorContext context) {
        return false;
    }
}
