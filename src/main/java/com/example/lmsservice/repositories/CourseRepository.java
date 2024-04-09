package com.example.lmsservice.repositories;

import com.example.lmsservice.models.Course;

import java.util.List;
import java.util.Optional;


public interface CourseRepository {

     List<Course> getAllCourses();

     Course createCourse(Course course);

     Course getCourseById(Long id);


     Optional<Course> getCourseByTitle( String name);

     Course updateCourse(Long id, Course course);

     void deleteCourse(Long id);
}
