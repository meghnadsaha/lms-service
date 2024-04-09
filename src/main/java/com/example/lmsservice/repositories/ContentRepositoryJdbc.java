package com.example.lmsservice.repositories;

import com.example.lmsservice.mapper.ContentRowMapper;
import com.example.lmsservice.models.Content;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContentRepositoryJdbc implements ContentRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContentRepositoryJdbc ( JdbcTemplate jdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Content> getContentByCourseId ( int courseId ) {
        String sql = "SELECT * FROM content WHERE course_id = ?";
        return jdbcTemplate.query(sql , new Object[]{courseId} , new ContentRowMapper());
    }

    @Override
    public boolean createContentForCourse ( int courseId , Content content ) {
        String sql = "INSERT INTO content (course_id, title, type) VALUES (?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql , courseId , content.getTitle() ,
                                                   String.valueOf(content.getContentType()));
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error while saving content due to " + e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Content> getContentById ( int id ) {
        String sql = "SELECT * FROM content WHERE id = ?";
        try {
            Content content = jdbcTemplate.queryForObject(sql , new Object[]{id} , new ContentRowMapper());
            return Optional.of(content);
        } catch (Exception e) {
            System.out.println("content not found with id: " + id + " " + e.getMessage());
            return Optional.empty();
        }
    }


    @Override
    public boolean updateContentById ( int id , Content content ) {
        String sql = "UPDATE content SET title = ?, type = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql , content.getTitle() , String.valueOf(content.getContentType()) ,
                                               id);
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteContentById ( int id ) {
        String sql = "DELETE FROM content WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql , id);
        return rowsAffected > 0;
    }
}
