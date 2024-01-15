package com.example.reddit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.reddit.model.Comment;
import com.example.reddit.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	public Comment findOne(Long id) {
		return commentRepository.findById(id).orElse(null);
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
