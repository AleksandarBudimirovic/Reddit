package com.example.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reddit.model.Flair;

public interface FlairRepository extends JpaRepository<Flair, Long>  {

}
