package com.example.lmsservice.repositories;

import com.example.lmsservice.models.Content;

import java.util.List;
import java.util.Optional;

public interface ContentRepository {
    List<Content> getContentByCourseId ( int courseId );

    boolean createContentForCourse ( int courseId , Content content );

    Optional<Content> getContentById ( int id );

    boolean updateContentById ( int id , Content content );

    boolean deleteContentById ( int id );
}
