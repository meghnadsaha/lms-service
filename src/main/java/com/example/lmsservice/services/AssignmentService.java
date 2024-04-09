package com.example.lmsservice.services;

import com.example.lmsservice.models.Assignment;
import com.example.lmsservice.models.Course;
import com.example.lmsservice.repositories.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService ( AssignmentRepository assignmentRepository ) {
        this.assignmentRepository = assignmentRepository;
    }

    public List<Assignment> getAssignmentsByCourseId ( int courseId ) {
        return assignmentRepository.getAssignmentsByCourseId(courseId);
    }

    public boolean createAssignmentForCourse ( int courseId , Assignment assignment ) {
        return assignmentRepository.createAssignmentForCourse(courseId , assignment);
    }

    public Optional<Assignment> getAssignmentById ( int id ) {
        return assignmentRepository.getAssignmentById(id);
    }

    public boolean updateAssignmentById ( int id , Assignment assignment ) {
        return assignmentRepository.updateAssignmentById(id , assignment);
    }

    public boolean deleteAssignmentById ( int id ) {
        return assignmentRepository.deleteAssignmentById(id);
    }
}
