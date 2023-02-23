package com.example.JPointV2.controller;

import com.example.JPointV2.dto.PostDto;
import com.example.JPointV2.model.Post;
import com.example.JPointV2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/create/new/post")
    public ResponseEntity<PostDto> createNewPost(@RequestBody PostDto postDto) {
        return postDto == null
                ? new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED)
                : new ResponseEntity<>(postService.createNewPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{postId}")
    public PostDto getPostById(@PathVariable("postId") Long _postId) {
        return postService.getPostById(_postId);
    }

    @GetMapping("/get/all/post")
    public ResponseEntity<List<PostDto>> getAllPost() {
        List<PostDto> posts = postService.getAllPost();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long _postId) {
        postService.deleteId(_postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{postId}")
    public void update(@RequestBody PostDto postDto, @PathVariable("postId") Long postId) {
        postService.updatePosts(postDto, postId);
    }
}
