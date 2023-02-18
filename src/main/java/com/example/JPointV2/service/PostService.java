package com.example.JPointV2.service;

import com.example.JPointV2.exception.AllException;
import com.example.JPointV2.model.Post;
import com.example.JPointV2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post createNewPost(@Validated Post _post) {
        return postRepository.save(_post);
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Transactional
    public void updatePosts(@Validated Post post, Long _postId) {
        Post _post = postRepository.findById(_postId).get();
        _post.setName(post.getName());
        _post.setDescription(post.getDescription());
    }

    @Transactional
    public void deleteId(@Validated Long _postId) {
        Optional<Post> post = postRepository.findById(_postId);
        if (post.isPresent()) {
            postRepository.deleteById(_postId);
        }
        throw new AllException("Должности с " + _postId + " не существует");
    }
}
