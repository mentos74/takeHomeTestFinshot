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
    public String addNewPost(
            @ModelAttribute("postCreateRequestDTO") @Valid PostCreateRequestDTO postCreateRequestDTO,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("postCreateRequestDTO", postCreateRequestDTO);
            return "/post/add";
        }

        postServices.createNewPost(postCreateRequestDTO);
        return "redirect:/list";
    }


    @GetMapping("/view/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        postServices.countViews(id);
        PostResponseDTO post = postServices.viewPost(id);
        model.addAttribute("post", post);
        return "/post/view";
    }



    @GetMapping("/edit/{id}")
    public String editNewPost(@PathVariable Long id, Model model) {
        PostResponseDTO post = postServices.viewPost(id);

        PostUpdateRequestDTO postUpdateRequestDTO = new PostUpdateRequestDTO();
        postUpdateRequestDTO.setPostId(post.getPostId());
        postUpdateRequestDTO.setTitle(post.getTitle());
        postUpdateRequestDTO.setContent(post.getContent());
        postUpdateRequestDTO.setAuthor(post.getAuthor());
        postUpdateRequestDTO.setPassword(post.getPassword());

        model.addAttribute("postUpdateRequestDTO", postUpdateRequestDTO);
        return "/post/edit";
    }

    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id,
                             @ModelAttribute("postUpdateRequestDTO") @Valid PostUpdateRequestDTO postUpdateRequestDTO,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postUpdateRequestDTO", postUpdateRequestDTO);
            return "/post/edit";
        }

        String existingPassword = postServices.checkPasswordExist(id);

        if (!existingPassword.equals(postUpdateRequestDTO.getPassword())) {
            bindingResult.rejectValue("password", "error.password", "Password is incorrect");
            model.addAttribute("postUpdateRequestDTO", postUpdateRequestDTO);
            return "/post/edit";
        }

        postServices.updatePost(postUpdateRequestDTO, id);
        return "redirect:/list";
    }




    @GetMapping("/delete/{id}")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {

        PostResponseDTO post = postServices.viewPost(id);
        model.addAttribute("post", post);
        return "/post/delete";
    }

    @PostMapping("/delete/{id}")
    public String deletePostWithValidation(@PathVariable Long id,
                                           @RequestParam("password") String password,
                                           Model model) {

        String existingPassword = postServices.checkPasswordExist(id);


        if (!existingPassword.equals(password)) {
            model.addAttribute("errorMessage", "Password is incorrect");
            model.addAttribute("post", postServices.viewPost(id));
            return "/post/delete";
        }

        postServices.deletePost(id);
        return "redirect:/list";
    }






}
