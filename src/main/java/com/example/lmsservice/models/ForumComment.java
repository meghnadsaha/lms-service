package com.example.lmsservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ForumComment {
    private Long id;
    private Long postId;
    private Long authorId;
    private String content;
    private LocalDateTime createdAt;
}
