package com.finshot.takehometest.services.impl;

import com.finshot.takehometest.dto.PostCreateRequestDTO;
import com.finshot.takehometest.dto.PostResponseDTO;
import com.finshot.takehometest.dto.PostUpdateRequestDTO;
import com.finshot.takehometest.entity.Post;
import com.finshot.takehometest.repository.PostRepository;
import com.finshot.takehometest.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServicesImpl implements PostServices {


    @Autowired
    PostRepository postRepository;


    @Override
    public List<PostResponseDTO> listPost() {
        return postRepository.findAll().stream().map((post) -> {
            PostResponseDTO dto = new PostResponseDTO();
            dto.setPostId(post.getPostId());
            dto.setTitle(post.getTitle());
            dto.setContent(post.getContent());
            dto.setAuthor(post.getAuthor());
            dto.setViews(post.getViews());
            dto.setCreatedAt(post.getCreatedAt());
            dto.setModifiedAt(post.getModifiedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void createNewPost(PostCreateRequestDTO dto) {
        Post post = new Post();
        post.setAuthor(dto.getAuthor());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        postRepository.save(post);

    }

    @Override
    public void updatePost(PostUpdateRequestDTO dto) {

    }

    @Override
    public void deletePost(String id) {

    }

    @Override
    public PostResponseDTO viewPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post not found"));
        post.setViews(post.getViews() );

        postRepository.save(post);

        PostResponseDTO dto = new PostResponseDTO();
        dto.setPostId(post.getPostId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAuthor(post.getAuthor());
        dto.setViews(post.getViews());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setModifiedAt(post.getModifiedAt());
        return dto;
    }

}
