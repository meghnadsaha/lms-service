package com.example.lmsservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Grade {
    private Long id;
    private Long assignmentId;
    private Long studentId;
    private BigDecimal grade;

    // Getters and setters
}
