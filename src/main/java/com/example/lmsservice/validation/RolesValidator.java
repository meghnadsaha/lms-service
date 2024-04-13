package com.example.lmsservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RolesValidator implements ConstraintValidator<AllowedRoles, Set<String>> {

    private static final Set<String> ALLOWED_ROLES = new HashSet<>(
            Arrays.asList("ROLE_ADMIN" , "ROLE_MODERATOR" , "ROLE_STUDENT" , "ROLE_INSTRUCTOR"));

    @Override
    public boolean isValid ( Set<String> roles , ConstraintValidatorContext context ) {
        if (roles == null) {
            return false;
        }

        for (String role : roles) {
            if (!ALLOWED_ROLES.contains(role)) {
                return false;
            }
        }
        return true;
    }
}
