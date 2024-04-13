package com.example.lmsservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD , ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomValidator.class)
@Documented
public @interface CustomValidation {
    String message () default "Invalid value";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};
}
