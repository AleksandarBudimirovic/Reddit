package com.example.reddit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.reddit.model.Comment;
import com.example.reddit.model.Post;
import com.example.reddit.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	public Comment findOne(Long id) {
		return commentRepository.findById(id).orElse(null);
	}
	
    public List<Comment> findByPostId(Long postId) {
        List<Comment> allComments = commentRepository.findAll();
        List<Comment> filteredComments = allComments.stream()
                .filter(comment -> comment.getPost() != null && comment.getPost().getId().equals(postId))
                .collect(Collectors.toList());

        if (filteredComments.isEmpty()) {
            System.err.println("No comments found for postId: " + postId);
        } else {
            for (Comment comment : filteredComments) {
                if (comment == null) {
                    System.err.println("Found null comment for postId: " + postId);
                } else {
                    System.out.println("Retrieved comment with ID: " + comment.getId());
                }
            }
        }

        return filteredComments;
    }
	
	
	public List<Comment> findAll() {

		return commentRepository.findAll();
	}
	

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
		
	}
	
	
	public void remove(Long id){
		commentRepository.deleteById(id);
	}
	
	public Page<Comment> findAll(Pageable page) {
		return commentRepository.findAll(page);
	}
	
}
