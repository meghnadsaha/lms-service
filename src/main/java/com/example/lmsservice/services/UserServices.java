package com.example.lmsservice.services;

import com.example.lmsservice.exception.EntityNotFoundException;
import com.example.lmsservice.models.User;
import com.example.lmsservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public User signup ( User user ) {
        try {
            return userRepository.saveUser(user);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to sign up user: " + exception.getMessage());
        }
    }


    public Optional<User> findByUsername ( String username ) throws EntityNotFoundException {
        return userRepository.findByUsername(username);
    }

}
