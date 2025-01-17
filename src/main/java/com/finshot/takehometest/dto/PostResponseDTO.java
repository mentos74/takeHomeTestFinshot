package com.finshot.takehometest.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostResponseDTO {

    private Long postId;

    private String title;

    private String author;

    private String content;

    private int views;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private String formattedCreatedDate;

    private String formattedModifiedAt;

    private String password;

    private String trimmedTitle;

    private String trimmedAuthor;
}
