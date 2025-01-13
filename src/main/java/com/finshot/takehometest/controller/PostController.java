package com.finshot.takehometest.controller;

import com.finshot.takehometest.dto.PostResponseDTO;
import com.finshot.takehometest.entity.Post;
import com.finshot.takehometest.services.PostServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class PostController {

    @Autowired
     PostServices postServices;

    @GetMapping("/v1/listbulletin")
    public String findAllPost(Model model) {
        List<PostResponseDTO> posts = postServices.listPost();
        model.addAttribute("posts",posts);
        return "/post/list";
    }
}
