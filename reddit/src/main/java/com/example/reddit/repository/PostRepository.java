package com.example.reddit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reddit.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>  {
	List<Post> findByCommunityId(Long communityId);

}
