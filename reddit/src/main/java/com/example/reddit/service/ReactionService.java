package com.example.reddit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.reddit.model.Reaction;
import com.example.reddit.repository.ReactionRepository;

public class ReactionService {

	@Autowired
	ReactionRepository reactionRepository;
	
	public Reaction findOne(Long id) {
		return reactionRepository.findById(id).orElse(null);
	}
	
	
	public List<Reaction> findAll() {

		return reactionRepository.findAll();
	}
	

	public Reaction save(Reaction reaction) {
		return reactionRepository.save(reaction);
		
	}
	
	
	public void remove(Long id){
		reactionRepository.deleteById(id);
	}
	
	public Page<Reaction> findAll(Pageable page) {
		return reactionRepository.findAll(page);
	}
	
}
