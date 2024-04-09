package com.example.lmsservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Course {
    private Long id;
    private String title;
    private String description;
    private Long instructorId;

    // Getters and setters
}
