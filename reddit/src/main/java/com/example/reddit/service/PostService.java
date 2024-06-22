package com.example.reddit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.reddit.model.Post;
import com.example.reddit.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	public Post findOne(Long id) {
		return postRepository.findById(id).orElse(null);
	}
	
    
    public List<Post> findByCommunityId(Long communityId) {
        return postRepository.findByCommunityId(communityId);
    }
	
	public List<Post> findAll() {

		return postRepository.findAll();
	}
	

	public Post save(Post post) {
		return postRepository.save(post);
		
	}
	
	
	public void remove(Long id){
		postRepository.deleteById(id);
	}
	
	public Page<Post> findAll(Pageable page) {
		return postRepository.findAll(page);
	}
	
}
