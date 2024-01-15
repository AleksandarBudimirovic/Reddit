package com.example.reddit.mapper;

import org.springframework.stereotype.Component;

import com.example.reddit.dto.CommentDTO;
import com.example.reddit.model.Comment;

@Component
public class CommentMapper {

    public CommentDTO modelToDto(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(comment.getId());
        commentDTO.setIsDeleted(comment.getIsDeleted());
        commentDTO.setText(comment.getText());
        commentDTO.setTimestamp(comment.getTimestamp());



        return commentDTO;
    }

    public Comment dtoToModel(CommentDTO commentDTO) {
        Comment comment = new Comment();

        comment.setId(commentDTO.getId());
        comment.setIsDeleted(commentDTO.getIsDeleted());
        comment.setText(commentDTO.getText());
        comment.setTimestamp(commentDTO.getTimestamp());



        return comment;
    }
}