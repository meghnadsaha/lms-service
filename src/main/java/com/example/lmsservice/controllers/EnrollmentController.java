package com.example.lmsservice.controllers;

import com.example.lmsservice.models.Enrollment;
import com.example.lmsservice.services.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Operation(summary = "Get all enrollments for a student")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudentId(@PathVariable("studentId") int studentId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
        return ResponseEntity.ok(enrollments);
    }

    @Operation(summary = "Enroll student in a course")
    @PostMapping("/enroll")
    public ResponseEntity<String> enrollStudent(@RequestBody Enrollment enrollment) {
        boolean enrolled = enrollmentService.enrollStudent(enrollment);
        if (enrolled) {
            return ResponseEntity.ok("Student enrolled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enrollment failed");
        }
    }

    @Operation(summary = "Withdraw student from a course")
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawStudent(@RequestBody Enrollment enrollment) {
        boolean withdrawn = enrollmentService.withdrawStudent(enrollment);
        if (withdrawn) {
            return ResponseEntity.ok("Student withdrawn successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Withdrawal failed");
        }
    }
}
