package com.example.lmsservice.services;

import com.example.lmsservice.models.ForumPost;
import com.example.lmsservice.repositories.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {


    @Autowired
    ForumRepository forumRepository;

    public List<ForumPost> getForumPostsForCourse ( Long courseId ) {
        return forumRepository.getForumPostsForCourse(courseId);
    }

    public boolean createForumPostForCourse ( Long courseId , ForumPost forumPost ) {
        return forumRepository.createForumPostForCourse(courseId , forumPost);

    }

    public ForumPost getForumPostById ( Long id ) {
        String sql = "SELECT * FROM forum_posts WHERE id = ?";
        return forumRepository.getForumPostById(id);
    }

    public ForumPost updateForumPostById ( Long id , ForumPost forumPost ) {
        String sql = "UPDATE forum_posts SET content = ? WHERE id = ?";
        return forumRepository.updateForumPostById(id , forumPost);
    }

    public void deleteForumPostById ( Long id ) {
        String sql = "DELETE FROM forum_posts WHERE id = ?";
        deleteForumPostById(id);

    }
}
