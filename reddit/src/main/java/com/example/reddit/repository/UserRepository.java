package com.example.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reddit.model.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	User findByUsername(String username);
}
