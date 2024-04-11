package com.example.lmsservice.mapper;

import com.example.lmsservice.models.ERole;
import com.example.lmsservice.models.Role;
import com.example.lmsservice.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow ( ResultSet rs , int rowNum ) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPasswordHash(rs.getString("password_Hash"));
        user.setEmail(rs.getString("email"));


        Role role = new Role();
        role.setId(rs.getInt("role_id"));
        role.setName(ERole.valueOf(rs.getString("role_name")));

        user.setRoles(Set.of(role));
        return user;
    }
}

