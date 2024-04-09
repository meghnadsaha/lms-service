package com.example.lmsservice.mapper;

import com.example.lmsservice.models.Assignment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignmentRowMapper implements RowMapper<Assignment> {

    @Override
    public Assignment mapRow( ResultSet rs, int rowNum) throws SQLException {
        Assignment assignment = new Assignment();
        assignment.setId(rs.getLong("id"));
        assignment.setCourseId(rs.getLong("course_id"));
        assignment.setTitle(rs.getString("title"));
        assignment.setDueDate(rs.getDate("due_date").toLocalDate());
        // Set other properties as needed
        return assignment;
    }
}
