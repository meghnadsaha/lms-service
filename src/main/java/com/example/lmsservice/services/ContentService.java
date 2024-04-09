package com.example.lmsservice.services;

import com.example.lmsservice.models.Content;
import com.example.lmsservice.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    ContentRepository contentRepository;

    public List<Content> getContentByCourseId ( int courseId ) {
        return contentRepository.getContentByCourseId(courseId);
    }

    public boolean createContentForCourse ( int courseId , Content content ) {
        return contentRepository.createContentForCourse(courseId , content);
    }

    public Optional<Content> getContentById ( int id ) {
        return contentRepository.getContentById(id);
    }

    public boolean updateContentById ( int id , Content content ) {
        return contentRepository.updateContentById(id , content);
    }

    public boolean deleteContentById ( int id ) {
        return contentRepository.deleteContentById(id);

    }
}
