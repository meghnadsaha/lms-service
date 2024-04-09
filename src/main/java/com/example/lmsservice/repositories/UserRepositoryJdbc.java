package com.example.lmsservice.repositories;


import com.example.lmsservice.mapper.UserRowMapper;
import com.example.lmsservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryJdbc implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public User findByUsername ( String username ) {
        String query = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(query , new Object[]{username} , new UserRowMapper());
    }


    @Override
    public User save ( User user ) {
        String query = "INSERT INTO users (username, passwordHash, email, role) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query , user.getUsername() , user.getPasswordHash() , user.getEmail() , user.getRole());
        return user;
    }

}
