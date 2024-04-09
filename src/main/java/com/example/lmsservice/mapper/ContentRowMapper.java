package com.example.lmsservice.mapper;

import com.example.lmsservice.models.Content;
import com.example.lmsservice.models.ContentType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ContentRowMapper implements RowMapper<Content> {
    @Override
    public Content mapRow ( ResultSet rs , int rowNum ) throws SQLException {
        Content content = new Content();
        content.setId(rs.getLong("id"));
        content.setCourseId(rs.getLong("course_id"));
        content.setTitle(rs.getString("title"));
        content.setContentType(ContentType.valueOf((rs.getString("type"))));
        return content;
    }
}