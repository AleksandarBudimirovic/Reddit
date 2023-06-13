package com.example.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reddit.model.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Long>  {

}
