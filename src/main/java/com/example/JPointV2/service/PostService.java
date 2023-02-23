package com.example.JPointV2.service;

import com.example.JPointV2.dto.PostDto;
import com.example.JPointV2.exception.AllException;
import com.example.JPointV2.mapper.PostMapper;
import com.example.JPointV2.model.Post;
import com.example.JPointV2.model.User;
import com.example.JPointV2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;


    @Transactional
    public PostDto createNewPost(@Validated PostDto postDto) {
        Post post = postMapper.convertDtoToPost(postDto);
        return postMapper.convertPostToDto(postRepository.save(post));
    }

    public PostDto getPostById(Long id) {
        return postMapper.convertPostToDto(Objects.requireNonNull(postRepository.findById(id)
                .orElse(null)));
    }

    public List<PostDto> getAllPost() {
        return  postRepository.findAll()
                .stream()
                .map(postMapper::convertPostToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updatePosts(@Validated PostDto postDto, Long _postId) {
        Post _post = postRepository.findById(_postId).get();
        _post.setName(postDto.getName());
        _post.setDescription(postDto.getDescription());

        postMapper.convertPostToDto(_post);
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
