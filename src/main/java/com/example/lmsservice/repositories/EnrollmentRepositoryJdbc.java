package com.example.lmsservice.repositories;

import com.example.lmsservice.mapper.EnrollmentRowMapper;
import com.example.lmsservice.mapper.UserRowMapper;
import com.example.lmsservice.models.Enrollment;
import com.example.lmsservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EnrollmentRepositoryJdbc implements EnrollmentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Enrollment> findByStudentId ( int studentId ) {
        String query = "SELECT * FROM enrollments WHERE student_id = ?";
        return jdbcTemplate.query(query , new Object[]{studentId} , new EnrollmentRowMapper());
    }

    @Override
    public boolean save ( Enrollment enrollment ) {
        String sql = "INSERT INTO enrollments (course_id, student_id, enrollment_date) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql , enrollment.getCourseId() , enrollment.getStudentId() ,
                                enrollment.getEnrollmentDate());
            return true;
        } catch (Exception e) {
            System.out.println("Error while creating enrollment due to " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete ( Enrollment enrollment ) {
        String sql = "DELETE FROM enrollments WHERE course_id = ? AND student_id = ?";
        try {
            jdbcTemplate.update(sql , enrollment.getCourseId() , enrollment.getStudentId());
            return true;
        } catch (DataAccessException e) {
            System.out.println("Error while deleting enrollment id+" + enrollment + "+ due to " + e.getMessage());
            return false;
        }
    }
}
