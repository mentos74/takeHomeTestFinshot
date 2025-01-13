    package com.finshot.takehometest.entity;

    import com.fasterxml.jackson.databind.PropertyNamingStrategies;
    import com.fasterxml.jackson.databind.annotation.JsonNaming;
    import jakarta.persistence.*;
    import lombok.Data;

    import java.io.Serial;
    import java.io.Serializable;
    import java.time.LocalDateTime;

    @Data
    @Entity
    @Table(name = "posts")
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public class Post implements Serializable {

        @Serial
        private static final long serialVersionUID = -7889938648939242355L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Long postId;

        public String title;

        public String author;

        public String content;

        @Column(nullable = false, columnDefinition = "int default 0")
        public int views = 0;

        public LocalDateTime createdAt;

        public LocalDateTime modifiedAt;

        @PrePersist
        public void prePersist() {
            this.createdAt = LocalDateTime.now();
            this.modifiedAt = LocalDateTime.now();
        }

        @PreUpdate
        public void preUpdate() {
            this.modifiedAt = LocalDateTime.now();
        }
    }
