package com.finshot.takehometest.services;

import com.finshot.takehometest.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface PostServices {

    public List<Post> findAll();

}