package com.example.lmsservice.repositories;

import com.example.lmsservice.models.Enrollment;

import java.util.List;

public interface EnrollmentRepository {

    List<Enrollment> findByStudentId( int studentId);

    boolean save(Enrollment enrollment);

    boolean delete(Enrollment enrollment);
}
