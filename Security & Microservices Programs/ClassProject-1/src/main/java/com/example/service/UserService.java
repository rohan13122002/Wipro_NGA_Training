package com.example.service;
	

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

	@Service
	public class UserService {
	    private final UserRepository repo;

	    public UserService(UserRepository repo) {
	        this.repo = repo;
	    }

	    public User getByUsername(String username) {
	        return repo.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
	    }
	}



