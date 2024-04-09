package com.example.lmsservice.services;

import com.example.lmsservice.models.Course;
import com.example.lmsservice.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    public Course createCourse(Course course) {
        return courseRepository.createCourse(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.getCourseById(id);
    }

    public Optional<Course> getCourseByTitle( String title) {
        return courseRepository.getCourseByTitle(title);
    }

    public Course updateCourse(Long id, Course course) {
        return courseRepository.updateCourse(id, course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteCourse(id);
    }
}
