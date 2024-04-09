package com.example.lmsservice.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String username;
    private String passwordHash;
    private String email;
    private Role Role;

}
