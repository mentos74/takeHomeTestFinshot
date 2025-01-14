package com.finshot.takehometest.services;

import com.finshot.takehometest.dto.PostCreateRequestDTO;
import com.finshot.takehometest.dto.PostResponseDTO;
import com.finshot.takehometest.dto.PostUpdateRequestDTO;
import com.finshot.takehometest.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface PostServices {

    public List<PostResponseDTO> listPost();
    public void createNewPost(PostCreateRequestDTO dto);
    public void updatePost(PostUpdateRequestDTO dto);
    public void deletePost(Long id);
    public PostResponseDTO viewPost(Long id);

}