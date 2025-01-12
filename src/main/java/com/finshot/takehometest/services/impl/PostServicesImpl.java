package com.finshot.takehometest.services.impl;

import com.finshot.takehometest.entity.Post;
import com.finshot.takehometest.repository.PostRepository;
import com.finshot.takehometest.services.PostServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServicesImpl implements PostServices {


    @Autowired
    PostRepository postRepository;


    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
