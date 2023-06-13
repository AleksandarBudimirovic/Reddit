package com.example.reddit.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.reddit.model.Ban;
import com.example.reddit.repository.BanRepository;



public class BanService {

	@Autowired
	BanRepository banRepository;
	
	public Ban findOne(Long id) {
		return banRepository.findById(id).orElse(null);
	}
	
	
	public List<Ban> findAll() {

		return banRepository.findAll();
	}
	

	public Ban save(Ban ban) {
		return banRepository.save(ban);
		
	}
	
	
	public void remove(Long id){
		banRepository.deleteById(id);
	}
	
	public Page<Ban> findAll(Pageable page) {
		return banRepository.findAll(page);
	}
	
	
}
