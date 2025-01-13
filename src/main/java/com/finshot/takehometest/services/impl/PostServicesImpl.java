package com.finshot.takehometest.services.impl;

import com.finshot.takehometest.dto.PostResponseDTO;
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
    public List<Post> findAll() {
        return postRepository.findAll();
    }

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

}
