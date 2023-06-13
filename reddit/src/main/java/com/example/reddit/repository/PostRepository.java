package com.example.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reddit.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>  {

}
