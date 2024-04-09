package com.example.lmsservice.repositories;

import com.example.lmsservice.models.Assignment;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository {

    List<Assignment> getAssignmentsByCourseId ( int courseId );


    boolean createAssignmentForCourse ( int courseId , Assignment assignment );

    Optional<Assignment> getAssignmentById ( int id );


    boolean updateAssignmentById ( int id , Assignment assignment );

    boolean deleteAssignmentById ( int id );
}
