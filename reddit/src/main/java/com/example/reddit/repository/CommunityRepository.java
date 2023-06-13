package com.example.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reddit.model.Community;

public interface CommunityRepository extends JpaRepository<Community, Long>  {

}
