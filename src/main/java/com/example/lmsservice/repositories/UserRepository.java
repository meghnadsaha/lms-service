package com.example.lmsservice.repositories;


import com.example.lmsservice.models.User;

public interface UserRepository {
    User findByUsername( String username);
    User save(User user);
}
