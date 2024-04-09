package com.example.lmsservice.mapper;

import com.example.lmsservice.models.Enrollment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentRowMapper implements RowMapper<Enrollment> {

    @Override
    public Enrollment mapRow ( ResultSet rs , int rowNum ) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(rs.getLong("id"));
        enrollment.setCourseId(rs.getLong("course_id"));
        enrollment.setStudentId(rs.getLong("student_id"));
        enrollment.setEnrollmentDate(rs.getDate("enrollment_date").toLocalDate());
        return enrollment;
    }
}
