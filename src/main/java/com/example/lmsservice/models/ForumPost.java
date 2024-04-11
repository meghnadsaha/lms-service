package com.example.lmsservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Setter
@Getter
@ToString
public class ForumPost {
    private Long id;
    private Long courseId;
    private Long authorId;
    private String content;
    private LocalDateTime createdAt;

    // Getters and setters
}
