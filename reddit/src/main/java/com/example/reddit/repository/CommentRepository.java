package com.example.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.reddit.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>  {

}
