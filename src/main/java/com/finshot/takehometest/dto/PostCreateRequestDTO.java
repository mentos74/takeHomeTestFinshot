package com.finshot.takehometest.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostCreateRequestDTO {

    private Long postId;

    @NotBlank(message = "Title must be filled")
    private String title;

    @NotBlank(message = "Password must be filled")
    private String password;

    private String author;

    private String content;

    private int views;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


}
