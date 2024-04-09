package com.example.lmsservice.controllers;


import com.example.lmsservice.exception.EntityNotFoundException;
import com.example.lmsservice.models.User;

import com.example.lmsservice.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;


    @PostMapping("/signup")
    public ResponseEntity<String> signup ( @RequestBody User user ) throws EntityNotFoundException {
        Optional<User> existingUser = userServices.findByUsername(user.getUsername());
        if (existingUser.isEmpty()) {
            userServices.signup(user);
            return ResponseEntity.status(HttpStatus.OK).body("Signup successful");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login ( @RequestParam String username , @RequestParam String password ) throws EntityNotFoundException {
        Optional<User> user = userServices.findByUsername(username);
        if (user.isEmpty() || !user.get().getPasswordHash().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        return ResponseEntity.ok("Login  successful");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout () {
        // Implement logout logic if needed
        return ResponseEntity.ok("Logout successful");
    }
}
