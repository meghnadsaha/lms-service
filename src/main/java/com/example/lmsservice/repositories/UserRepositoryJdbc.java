package com.example.lmsservice.repositories;


import com.example.lmsservice.mapper.UserRowMapper;
import com.example.lmsservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
            User user = jdbcTemplate.queryForObject(query , new Object[]{username} ,
                                                    BeanPropertyRowMapper.newInstance(User.class));
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // User not found
        }
    }


    @Override
    public User saveUser ( User user ) {
        String sql = "INSERT INTO users (username, password_Hash, email, role_id) VALUES (?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, user.getUsername(), user.getPasswordHash(), user.getEmail(), user.getRoles().iterator().next().getId());

        } catch (Exception e) {
            System.out.println("Error while saving assignment due to " + e.getMessage());
        }
        return user;
    }

    @Override
    public Optional<List<User>> findByUserRole ( String userRole ) {
        String sql = "SELECT u.id, u.username, u.password_hash, u.email, r.id as role_id, r.name as role_name " +
                "FROM users u " +
                "JOIN roles r ON u.role_id = r.id " +
                "WHERE r.name = ?";
        try {

            List<User> users = jdbcTemplate.query(sql , new Object[]{userRole} ,new UserRowMapper());
            return Optional.ofNullable(users.isEmpty()?null:users);
        } catch (Exception e) {
            System.out.println("Error while finding user by role due to " + e.getMessage());
            return Optional.empty(); // User not found
        }
    }

}
