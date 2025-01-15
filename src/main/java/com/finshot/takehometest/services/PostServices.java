package com.finshot.takehometest.services;

import com.finshot.takehometest.dto.PostCreateRequestDTO;
import com.finshot.takehometest.dto.PostResponseDTO;
import com.finshot.takehometest.dto.PostUpdateRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface PostServices {

    public List<PostResponseDTO> listPost();
    public void createNewPost(PostCreateRequestDTO dto);
    public void updatePost(PostUpdateRequestDTO dto, Long id);
    public void deletePost(Long id);
    public PostResponseDTO viewPost(Long id);
    public String checkPasswordExist(Long id);
    public void countViews(Long id);

}