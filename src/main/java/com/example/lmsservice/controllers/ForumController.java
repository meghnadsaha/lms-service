package com.example.lmsservice.controllers;

import com.example.lmsservice.models.ForumPost;
import com.example.lmsservice.services.ForumService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forum")
public class ForumController {

    private final ForumService forumService;

    public ForumController ( ForumService forumService ) {
        this.forumService = forumService;
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get all forum posts for a course")
    public ResponseEntity<List<ForumPost>> getForumPostsForCourse ( @PathVariable Long courseId ) {
        List<ForumPost> forumPosts = forumService.getForumPostsForCourse(courseId);
        return ResponseEntity.ok(forumPosts);
    }

    @PostMapping("/course/{courseId}")
    @Operation(summary = "Create a new forum post for a course")
    public ResponseEntity<String> createForumPostForCourse ( @PathVariable Long courseId , @RequestBody ForumPost forumPost ) {

        boolean createdPost = forumService.createForumPostForCourse(courseId , forumPost);
        if (createdPost) {
            return ResponseEntity.ok("Forum created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create forum");
        }
    }

    @GetMapping("/post/{id}")
    @Operation(summary = "Get a forum post by its ID")
    public ResponseEntity<ForumPost> getForumPostById ( @PathVariable Long id ) {
        ForumPost forumPost = forumService.getForumPostById(id);
        return ResponseEntity.ok(forumPost);
    }

    @PutMapping("/post/{id}")
    @Operation(summary = "Update a forum post by its ID")
    public ResponseEntity<ForumPost> updateForumPostById ( @PathVariable Long id , @RequestBody ForumPost forumPost ) {
        ForumPost updatedPost = forumService.updateForumPostById(id , forumPost);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/post/{id}")
    @Operation(summary = "Delete a forum post by its ID")
    public ResponseEntity<Void> deleteForumPostById ( @PathVariable Long id ) {
        forumService.deleteForumPostById(id);
        return ResponseEntity.noContent().build();
    }
}
