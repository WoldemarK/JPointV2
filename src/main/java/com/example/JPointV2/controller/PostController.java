package com.example.JPointV2.controller;

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
    public ResponseEntity<Post> createNewPost(@RequestBody Post _post) {
        return _post == null
                ? new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED)
                : new ResponseEntity<>(postService.createNewPost(_post), HttpStatus.CREATED);
    }

    @GetMapping("/get/{postId}")
    public Optional<Post> getPostById(@PathVariable("postId") Long _postId) {
        return postService.getPostById(_postId);
    }

    @GetMapping("/get/all/post")
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> posts = postService.getAllPost();
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
    public void update(@RequestBody Post post, @PathVariable("postId") Long postId) {
        postService.updatePosts(post, postId);
    }
}
