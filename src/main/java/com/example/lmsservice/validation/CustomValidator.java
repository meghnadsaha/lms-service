package com.example.lmsservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<CustomValidation, String> {

    @Override
    public boolean isValid ( String value , ConstraintValidatorContext context ) {
        // Implement your validation logic here
        return value != null && value.startsWith("custom");
    }
}
