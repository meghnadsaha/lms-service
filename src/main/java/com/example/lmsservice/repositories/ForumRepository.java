package com.example.lmsservice.repositories;

import com.example.lmsservice.mapper.ForumPostMapper;
import com.example.lmsservice.models.ForumPost;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ForumRepository {

    private final JdbcTemplate jdbcTemplate;

    public ForumRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ForumPost> getForumPostsForCourse( Long courseId) {
        String sql = "SELECT * FROM forum_posts WHERE course_id = ?";
        return jdbcTemplate.query(sql, new Object[]{courseId}, new ForumPostMapper());
    }

    public boolean createForumPostForCourse( Long courseId, ForumPost forumPost) {
        String sql = "INSERT INTO forum_posts (course_id, author_id, content) VALUES (?, ?, ?)";

        try {
            int rowsAffected =jdbcTemplate.update(sql, courseId, forumPost.getAuthorId(), forumPost.getContent());
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error while saving assignment due to " + e.getMessage());
            return false;
        }
    }

    public ForumPost getForumPostById(Long id) {
        String sql = "SELECT * FROM forum_posts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ForumPostMapper());
    }

    public ForumPost updateForumPostById(Long id, ForumPost forumPost) {
        String sql = "UPDATE forum_posts SET content = ? WHERE id = ?";
        jdbcTemplate.update(sql, forumPost.getContent(), id);
        return forumPost;
    }

    public void deleteForumPostById(Long id) {
        String sql = "DELETE FROM forum_posts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
