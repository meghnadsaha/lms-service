package com.example.lmsservice.services;

import com.example.lmsservice.models.User;
import com.example.lmsservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public void signup ( User user ) {
        try {
            userRepository.saveUser(user);
        } catch (Exception e) {
            System.out.println("Error during signup due to " + e.getMessage());
        }
    }

    public Optional<User> findByUsername ( String username ) {
        return userRepository.findByUsername(username);
    }

    public Optional<List<User>> findByUserRole ( String userRole ) {
        return userRepository.findByUserRole(userRole);
    }

}
