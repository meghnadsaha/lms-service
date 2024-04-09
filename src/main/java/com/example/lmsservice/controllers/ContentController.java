package com.example.lmsservice.controllers;


import com.example.lmsservice.models.Content;
import com.example.lmsservice.services.ContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService contentService;

    public ContentController ( ContentService contentService ) {
        this.contentService = contentService;
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get all content for a course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved content") ,
            @ApiResponse(responseCode = "404", description = "Content not found for the course")
    })
    public ResponseEntity<List<Content>> getContentByCourseId ( @PathVariable("courseId") int courseId ) {
        List<Content> contentList = contentService.getContentByCourseId(courseId);
        return ResponseEntity.ok(contentList);
    }

    @PostMapping("/course/{courseId}")
    @Operation(summary = "Create new content for a course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Content created successfully") ,
            @ApiResponse(responseCode = "400", description = "Failed to create content")
    })
    public ResponseEntity<String> createContentForCourse ( @PathVariable("courseId") int courseId , @RequestBody Content content ) {
        boolean created = contentService.createContentForCourse(courseId , content);
        if (created) {
            return ResponseEntity.ok("Content created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create content");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get content by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved content") ,
            @ApiResponse(responseCode = "404", description = "Content not found")
    })
    public ResponseEntity<Content> getContentById ( @PathVariable("id") int id ) {
        Optional<Content> content = contentService.getContentById(id);
        if (!content.isEmpty()) {
            return ResponseEntity.ok(content.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update content by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Content updated successfully") ,
            @ApiResponse(responseCode = "400", description = "Failed to update content")
    })
    public ResponseEntity<String> updateContentById ( @PathVariable("id") int id , @RequestBody Content content ) {
        boolean updated = contentService.updateContentById(id , content);
        if (updated) {
            return ResponseEntity.ok("Content updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update content");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete content by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Content deleted successfully") ,
            @ApiResponse(responseCode = "400", description = "Failed to delete content")
    })
    public ResponseEntity<String> deleteContentById ( @PathVariable("id") int id ) {
        boolean deleted = contentService.deleteContentById(id);
        if (deleted) {
            return ResponseEntity.ok("Content deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete content");
        }
    }
}
