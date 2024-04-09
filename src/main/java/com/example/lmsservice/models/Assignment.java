package com.example.lmsservice.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Assignment {
    private Long id;
    private Long courseId;
    private String title;
    private LocalDate dueDate;

    // Getters and setters
}
