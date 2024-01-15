package com.example.reddit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.reddit.model.Community;
import com.example.reddit.repository.CommunityRepository;

@Service
public class CommunityService {

	@Autowired
	CommunityRepository communityRepository;
	
	public Community findOne(Long id) {
		return communityRepository.findById(id).orElse(null);
	}
	
	
	public List<Community> findAll() {

		return communityRepository.findAll();
	}
	

	public Community save(Community community) {
		return communityRepository.save(community);
		
	}
	
	
	public void remove(Long id){
		communityRepository.deleteById(id);
	}
	
	public Page<Community> findAll(Pageable page) {
		return communityRepository.findAll(page);
	}
	
}
