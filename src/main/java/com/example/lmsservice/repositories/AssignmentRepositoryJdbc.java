package com.example.lmsservice.repositories;

import com.example.lmsservice.mapper.AssignmentRowMapper;
import com.example.lmsservice.mapper.ContentRowMapper;
import com.example.lmsservice.mapper.CourseRowMapper;
import com.example.lmsservice.models.Assignment;
import com.example.lmsservice.models.Content;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class AssignmentRepositoryJdbc implements AssignmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public AssignmentRepositoryJdbc ( JdbcTemplate jdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Assignment> getAssignmentsByCourseId ( int courseId ) {
        String sql = "SELECT * FROM assignments WHERE course_id = ?";
        try {
            return jdbcTemplate.query(sql , new Object[]{courseId} , new AssignmentRowMapper());
        } catch (Exception e) {
            System.out.println("Assignment not found with id: " + courseId + " due to " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public boolean createAssignmentForCourse ( int courseId , Assignment assignment ) {
        String sql = "INSERT INTO assignments (course_id, title, due_date) VALUES (?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql , assignment.getCourseId() , assignment.getTitle() ,
                                                   assignment.getDueDate()
            );
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error while saving assignment due to " + e.getMessage());
            return false;
        }
    }


    @Override
    public Optional<Assignment> getAssignmentById ( int id ) {
        String sql = "SELECT * FROM Assignment WHERE id = ?";
        try {
            Assignment assignment = jdbcTemplate.queryForObject(sql , new Object[]{id} , new AssignmentRowMapper());
            return Optional.of(assignment);
        } catch (Exception e) {
            System.out.println("Assignment not found with id: " + id + " " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean updateAssignmentById ( int id , Assignment assignment ) {
        String sql = "UPDATE assignments SET title = ?, due_date = ? WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql , assignment.getTitle() , assignment.getDueDate() , id);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error while updating assignment due to " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAssignmentById ( int id ) {
        String sql = "DELETE FROM assignments WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql , id);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error while deleting assignment due to " + e.getMessage());
            return false;
        }
    }
}
