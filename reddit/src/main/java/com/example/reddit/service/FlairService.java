package com.example.reddit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.reddit.model.Flair;
import com.example.reddit.repository.FlairRepository;

@Service
public class FlairService {

	@Autowired
	FlairRepository flairRepository;
	
	public Flair findOne(Long id) {
		return flairRepository.findById(id).orElse(null);
	}
	
	
	public List<Flair> findAll() {

		return flairRepository.findAll();
	}
	

	public Flair save(Flair flair) {
		return flairRepository.save(flair);
		
	}
	
	
	public void remove(Long id){
		flairRepository.deleteById(id);
	}
	
	public Page<Flair> findAll(Pageable page) {
		return flairRepository.findAll(page);
	}
	
}
