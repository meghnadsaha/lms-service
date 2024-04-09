package com.example.lmsservice.mapper;

import com.example.lmsservice.models.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow ( ResultSet rs , int rowNum ) throws SQLException {
        Course course = new Course();
        course.setId(rs.getLong("id"));
        course.setTitle(rs.getString("title"));
        course.setDescription(rs.getString("description"));
        course.setInstructorId(rs.getLong("instructor_id"));
        return course;
    }
}