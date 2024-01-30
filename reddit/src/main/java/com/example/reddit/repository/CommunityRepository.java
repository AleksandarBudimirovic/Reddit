package com.example.reddit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reddit.model.Community;

public interface CommunityRepository extends JpaRepository<Community, Long>  {

	//List<Community> findAllByIds(List<Long> communityIds);
	
}
