package com.example.lmsservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Enrollment {
    private Long id;
    private Long courseId;
    private Long studentId;
    private LocalDate enrollmentDate;

    // Getters and setters
}
