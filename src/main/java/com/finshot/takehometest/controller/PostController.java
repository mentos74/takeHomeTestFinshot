package com.finshot.takehometest.controller;

import com.finshot.takehometest.dto.PostCreateRequestDTO;
import com.finshot.takehometest.dto.PostResponseDTO;
import jakarta.validation.Valid;
import com.finshot.takehometest.services.PostServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class PostController {

    @Autowired
     PostServices postServices;

    @GetMapping("/list")
    public String findAllPost(Model model) {
        List<PostResponseDTO> posts = postServices.listPost();
        model.addAttribute("posts",posts);
        return "/post/list";
    }

    @GetMapping("/add")
    public String addPost(Model model) {
        PostCreateRequestDTO postCreateRequestDTO = new PostCreateRequestDTO();
        model.addAttribute("postCreateRequestDTO", postCreateRequestDTO);
        return "/post/add";
    }

    @PostMapping("/add")
    public String addNewPost(@ModelAttribute("postCreateRequestDTO") @Valid PostCreateRequestDTO postCreateRequestDTO,
                             BindingResult bindingResult,
                             Errors errors ,
                             Model model) {

        if(errors.hasErrors()){
            model.addAttribute("postCreateRequestDTO",postCreateRequestDTO);
            return "/post/add";
        }

        postServices.createNewPost(postCreateRequestDTO);
        return "redirect:/list";
    }


}
