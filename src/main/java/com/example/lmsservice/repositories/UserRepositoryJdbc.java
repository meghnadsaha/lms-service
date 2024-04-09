package com.example.lmsservice.repositories;


import com.example.lmsservice.mapper.UserRowMapper;
import com.example.lmsservice.models.User;
import com.example.lmsservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJdbc implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Optional<User> findByUsername ( String username ) {
        String query = "SELECT * FROM users WHERE username = ?";
        try {
            User user = jdbcTemplate.queryForObject(query , new Object[]{username} , new UserRowMapper());
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // User not found
        }
    }


    @Override
    public User saveUser ( User user ) {
        String query = "INSERT INTO users (username, passwordHash, email, role) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query , user.getUsername() , user.getPasswordHash() , user.getEmail() , String.valueOf(user.getRole()));
        return user;
    }

}
