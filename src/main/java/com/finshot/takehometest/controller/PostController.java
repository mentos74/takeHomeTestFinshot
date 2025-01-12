package com.finshot.takehometest.controller;

import com.finshot.takehometest.entity.Post;
import com.finshot.takehometest.services.PostServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class PostController {

    @Autowired
    PostServices postServices;

    @GetMapping("/v1/listbulletin")
    public ResponseEntity<List<Post>> listPost() {
        List<Post> postList = postServices.findAll();
        return ResponseEntity.ok(postList);
    }
}
