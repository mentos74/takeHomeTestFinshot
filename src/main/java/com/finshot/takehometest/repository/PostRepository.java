package com.finshot.takehometest.repository;

import com.finshot.takehometest.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository <Post, Long> {
    List<Post> findByDeletedFalseOrderByCreatedAtDesc();

}
