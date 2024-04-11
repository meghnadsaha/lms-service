package com.example.lmsservice.controllers;


import com.example.lmsservice.models.User;
import com.example.lmsservice.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;


    @PostMapping("/signup")
    public ResponseEntity<String> signup ( @RequestBody User user )  {
        Optional<User> existingUser = userServices.findByUsername(user.getUsername());
        if (existingUser.isEmpty()) {
            userServices.signup(user);
            return ResponseEntity.status(HttpStatus.OK).body("Signup successful");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login ( @RequestParam String username , @RequestParam String password ){
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

    @GetMapping("/{userRole}")
    @Operation(summary = "Get Users by role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved assignment") ,
            @ApiResponse(responseCode = "404", description = "Assignment not found")
    })
    public ResponseEntity<List<User>> getUsersByRole ( @PathVariable("userRole") String userRole ) {
        Optional<List<User>> content = userServices.findByUserRole(userRole);
        if (!content.isEmpty()) {
            return ResponseEntity.ok(content.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
