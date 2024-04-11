package com.example.lmsservice.mapper;
import com.example.lmsservice.models.ForumPost;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForumPostMapper implements RowMapper<ForumPost> {

    @Override
    public ForumPost mapRow(ResultSet rs, int rowNum) throws SQLException {
        ForumPost forumPost = new ForumPost();
        forumPost.setId(rs.getLong("id"));
        forumPost.setCourseId(rs.getLong("course_id"));
        forumPost.setAuthorId(rs.getLong("author_id"));
        forumPost.setContent(rs.getString("content"));
        forumPost.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        // Set other properties as needed
        return forumPost;
    }
}
