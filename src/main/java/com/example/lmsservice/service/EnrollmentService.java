package com.example.lmsservice.service;

import com.example.lmsservice.models.Enrollment;
import com.example.lmsservice.repositories.EnrollmentRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepositoryJdbc enrollmentRepositoryJdbc;

    public List<Enrollment> getEnrollmentsByStudentId ( int studentId ) {
        return enrollmentRepositoryJdbc.findByStudentId(studentId);
    }

    public boolean enrollStudent ( Enrollment enrollment ) {
        return enrollmentRepositoryJdbc.save(enrollment);
    }

    public boolean withdrawStudent ( Enrollment enrollment ) {
        return enrollmentRepositoryJdbc.delete(enrollment);
    }
}
