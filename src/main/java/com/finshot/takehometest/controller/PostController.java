package com.finshot.takehometest.controller;

import com.finshot.takehometest.dto.PostCreateRequestDTO;
import com.finshot.takehometest.dto.PostResponseDTO;
import com.finshot.takehometest.dto.PostUpdateRequestDTO;
import jakarta.validation.Valid;
import com.finshot.takehometest.services.PostServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
public class PostController {

    @Autowired
    PostServices postServices;

    @GetMapping("/list")
    public String findAllPost(Model model) {
        List<PostResponseDTO> posts = postServices.listPost();
        model.addAttribute("posts", posts);
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
                             Errors errors,
                             Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("postCreateRequestDTO", postCreateRequestDTO);
            return "/post/add";
        }

        postServices.createNewPost(postCreateRequestDTO);
        return "redirect:/list";
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        PostResponseDTO post = postServices.viewPost(id);
        model.addAttribute("post", post);
        return "/post/view";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        PostResponseDTO post = postServices.viewPost(id);

        PostUpdateRequestDTO updateRequest = new PostUpdateRequestDTO();
        updateRequest.setPostId(post.getPostId());
        updateRequest.setTitle(post.getTitle());
        updateRequest.setContent(post.getContent());
        updateRequest.setContent(post.getAuthor());
        
        model.addAttribute("postUpdateRequestDTO", updateRequest);
        return "/post/edit";
    }

    @PostMapping("/edit")
    public String updatePost(@ModelAttribute("postUpdateRequestDTO") @Valid PostUpdateRequestDTO updateRequestDTO,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postUpdateRequestDTO", updateRequestDTO);
            return "/post/edit";
        }

        postServices.updatePost(updateRequestDTO);
        return "redirect:/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postServices.deletePost(id);
        return "redirect:/list";
    }


}
