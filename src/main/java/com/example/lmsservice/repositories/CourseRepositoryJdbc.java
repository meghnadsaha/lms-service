package com.example.lmsservice.repositories;

import com.example.lmsservice.mapper.CourseRowMapper;
import com.example.lmsservice.models.Course;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryJdbc implements CourseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Course> getAllCourses () {
        return jdbcTemplate.query("SELECT * FROM courses" , new CourseRowMapper());
    }

    @Override
    public Course createCourse ( Course course ) {
        try {
            String sql = "INSERT INTO courses (title, description, instructor_id) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql , course.getTitle() , course.getDescription() , course.getInstructorId());
            return course;
        } catch (DataAccessException e) {
            throw new ValidationException("Failed to create course.");
        }
    }

    @Override
    public Course getCourseById ( Long id ) {
        try {
            String sql = "SELECT * FROM courses WHERE id = ?";
            return jdbcTemplate.queryForObject(sql , new Object[]{id} , new CourseRowMapper());
        } catch (DataAccessException e) {
            throw new ValidationException("Course not found with id: " + id);
        }
    }

    @Override
    public Optional<Course> getCourseByTitle ( String title ) {
        String sql = "SELECT * FROM courses WHERE title = ?";
        try {
            Course course = jdbcTemplate.queryForObject(sql , new Object[]{title} , new CourseRowMapper());
            return Optional.of(course);
        } catch (Exception e) {
            System.out.println("Course not found with title: " + title+" "+e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Course updateCourse ( Long id , Course course ) {
//        jdbcTemplate.update("UPDATE courses SET title = ?, description = ?, instructor_id = ? WHERE id = ?" ,
//                            course.getTitle() , course.getDescription() , course.getInstructorId() , id);
//        return course;

        try {
            String sql = "UPDATE courses SET title = ?, description = ?, instructor_id = ? WHERE id = ?";
            jdbcTemplate.update(sql , course.getTitle() , course.getDescription() , course.getInstructorId() , id);
//            course.setId(id);
            return course;
        } catch (DataAccessException e) {
            throw new ValidationException("Failed to update course with id: " + id);
        }
    }

    @Override
    public void deleteCourse ( Long id ) {
        jdbcTemplate.update("DELETE FROM courses WHERE id = ?" , id);
    }

}
