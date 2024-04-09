package com.example.lmsservice.controllers;

import com.example.lmsservice.models.Course;
import com.example.lmsservice.models.User;
import com.example.lmsservice.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/get-all-courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/create-course")
    public ResponseEntity<String> createCourse( @RequestBody Course course) {
        Optional<Course> existingCourse = courseService.getCourseByTitle(course.getTitle());
        if (existingCourse.isEmpty()) {
            courseService.createCourse(course);
            return ResponseEntity.status(HttpStatus.OK).body("Course saved successful");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course name already exists");
        }
    }

    @GetMapping("/get-course-by-id/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/update-course/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/delete-course/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
