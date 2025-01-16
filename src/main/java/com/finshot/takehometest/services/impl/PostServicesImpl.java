package com.finshot.takehometest.services.impl;

import com.finshot.takehometest.dto.PostCreateRequestDTO;
import com.finshot.takehometest.dto.PostResponseDTO;
import com.finshot.takehometest.dto.PostUpdateRequestDTO;
import com.finshot.takehometest.entity.Post;
import com.finshot.takehometest.repository.PostRepository;
import com.finshot.takehometest.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServicesImpl implements PostServices {


    @Autowired
    PostRepository postRepository;


    @Override
    public List<PostResponseDTO> listPost() {
        return postRepository.findByDeletedFalseOrderByCreatedAtDesc().stream().map((post) -> {
            PostResponseDTO dto = new PostResponseDTO();

            String title = post.getTitle();
            if (title.matches(".*[가-힣].*")) {
                dto.setTrimmedTitle(title.substring(0, Math.min(title.length(), 50)));
            } else {
                dto.setTrimmedTitle(title.substring(0, Math.min(title.length(), 100)));
            }

            String author = post.getAuthor();
            if (author.matches(".*[가-힣].*")) {
                dto.setTrimmedAuthor(author.substring(0, Math.min(author.length(), 10)));
            } else {
                dto.setTrimmedAuthor(author);
            }

            System.out.println("trimmed1 >>"+dto.getTrimmedAuthor());
            System.out.println("trimmed2 >>"+dto.getTrimmedTitle());


            dto.setPostId(post.getPostId());
            dto.setAuthor(post.getAuthor());
            dto.setTitle(post.getTitle());
            dto.setContent(post.getContent());
            dto.setViews(post.getViews());
            dto.setCreatedAt(post.getCreatedAt());
            dto.setModifiedAt(post.getModifiedAt());
            dto.setPassword(post.getPassword());
            dto.setFormattedCreatedDate(post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            dto.setFormattedModifiedAt(post.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void createNewPost(PostCreateRequestDTO dto) {
        Post post = new Post();
        post.setAuthor(dto.getAuthor());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setPassword(dto.getPassword());
        post.setDeleted(false);
        postRepository.save(post);

    }

    @Override
    public void updatePost(PostUpdateRequestDTO dto, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getAuthor());
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setDeleted(true);
        postRepository.save(post);

    }

    @Override
    public PostResponseDTO viewPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post not found"));


        PostResponseDTO dto = new PostResponseDTO();
        dto.setPostId(post.getPostId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAuthor(post.getAuthor());
        dto.setViews(post.getViews());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setModifiedAt(post.getModifiedAt());
        dto.setPassword(post.getPassword());
        dto.setFormattedCreatedDate(post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        dto.setFormattedModifiedAt(post.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return dto;
    }

    @Override
    public String checkPasswordExist(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return post.getPassword();
    }

    @Override
    public void countViews(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post not found"));
        post.setViews(post.getViews()+1);

        postRepository.save(post);
    }

}
