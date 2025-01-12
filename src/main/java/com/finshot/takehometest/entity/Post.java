package com.finshot.takehometest.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;

    private String author;

    private String content;

    private int views;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


}
