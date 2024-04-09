package com.example.lmsservice.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Content {
    private Long id;
    private Long courseId;
    private String title;
    private String type;

    // Getters and setters
}
