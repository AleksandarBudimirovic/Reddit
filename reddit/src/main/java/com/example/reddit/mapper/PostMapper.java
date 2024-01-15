package com.example.reddit.mapper;

import com.example.reddit.dto.PostDTO;
import com.example.reddit.model.Post;

import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostDTO modelToDto(Post post) {
        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setCreationDate(post.getCreationDate());
        postDTO.setImagePath(post.getImagePath());
        postDTO.setTitle(post.getTitle());
        postDTO.setText(post.getText());

        return postDTO;
    }

    public Post dtoToModel(PostDTO postDTO) {
        Post post = new Post();

        post.setId(postDTO.getId());
        post.setCreationDate(postDTO.getCreationDate());
        post.setImagePath(postDTO.getImagePath());
        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());


        return post;
    }
}
