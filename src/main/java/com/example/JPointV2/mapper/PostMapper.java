package com.example.JPointV2.mapper;
import com.example.JPointV2.dto.PostDto;
import com.example.JPointV2.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostDto convertPostToDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .name(post.getName())
                .description(post.getDescription())
                .build();
    }

    public Post convertDtoToPost(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .name(postDto.getName())
                .description(postDto.getDescription())
                .build();
    }
}
