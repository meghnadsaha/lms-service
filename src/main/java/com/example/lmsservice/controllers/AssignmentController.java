package com.example.lmsservice.controllers;

import com.example.lmsservice.models.Assignment;
import com.example.lmsservice.models.Content;
import com.example.lmsservice.services.AssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController ( AssignmentService assignmentService ) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get all assignments for a course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved assignments") ,
            @ApiResponse(responseCode = "404", description = "No assignments found for the course")
    })
    public ResponseEntity<List<Assignment>> getAssignmentsByCourseId ( @PathVariable("courseId") int courseId ) {
        List<Assignment> contentList = assignmentService.getAssignmentsByCourseId(courseId);
        return ResponseEntity.ok(contentList);
    }

    @PostMapping("/course/{courseId}")
    @Operation(summary = "Create a new assignment for a course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment created successfully") ,
            @ApiResponse(responseCode = "400", description = "Failed to create assignment")
    })
    public ResponseEntity<String> createAssignmentForCourse ( @PathVariable("courseId") int courseId , @RequestBody Assignment assignment ) {
        boolean created = assignmentService.createAssignmentForCourse(courseId , assignment);
        if (created) {
            return ResponseEntity.ok("Assignment created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create assignment");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an assignment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved assignment") ,
            @ApiResponse(responseCode = "404", description = "Assignment not found")
    })
    public ResponseEntity<Assignment> getAssignmentById ( @PathVariable("id") int id ) {
        Optional<Assignment> content = assignmentService.getAssignmentById(id);
        if (!content.isEmpty()) {
            return ResponseEntity.ok(content.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an assignment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment updated successfully") ,
            @ApiResponse(responseCode = "400", description = "Failed to update assignment")
    })
    public ResponseEntity<String> updateAssignmentById ( @PathVariable("id") int id , @RequestBody Assignment assignment ) {
        boolean updated = assignmentService.updateAssignmentById(id , assignment);
        if (updated) {
            return ResponseEntity.ok("Assignment updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update assignment");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an assignment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment deleted successfully") ,
            @ApiResponse(responseCode = "400", description = "Failed to delete assignment")
    })
    public ResponseEntity<String> deleteAssignmentById ( @PathVariable("id") int id ) {
        boolean deleted = assignmentService.deleteAssignmentById(id);
        if (deleted) {
            return ResponseEntity.ok("Assignment deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete assignment");
        }
    }
}
