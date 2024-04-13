package com.example.lmsservice.dto;

import com.example.lmsservice.validation.AllowedRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SaveUserDTO {

    @NotNull
    @NotEmpty
//    @CustomValidation
    private String name;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String password;

    @NotNull
    @NotEmpty
    @AllowedRoles
    private Set<String> roles = new HashSet<>();


}
