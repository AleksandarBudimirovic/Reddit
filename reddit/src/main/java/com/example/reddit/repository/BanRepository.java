package com.example.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reddit.model.Ban;


public interface BanRepository extends JpaRepository<Ban, Long>  {

}