package com.example.lmsservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class RolesValidator implements ConstraintValidator<ValidRoles, List<String>> {

    private static final List<String> ALLOWED_ROLES = Arrays.asList("ROLE_ADMIN" , "ROLE_MODERATOR" , "ROLE_STUDENT" ,
                                                                    "ROLE_INSTRUCTOR");

    @Override
    public void initialize ( ValidRoles constraintAnnotation ) {
    }

    @Override
    public boolean isValid ( List<String> roles , ConstraintValidatorContext constraintValidatorContext ) {
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
