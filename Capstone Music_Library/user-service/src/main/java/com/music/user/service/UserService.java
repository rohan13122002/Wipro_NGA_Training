package com.music.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.user.entity.User;
import com.music.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private final UserRepository repo;
	
	//CONSTRUCTOR INJECTION
	public UserService(UserRepository repo)
	{
		this.repo= repo;
	}
	
	//SAVE USER
	public User register(User u)
	{
		return repo.save(u);
	}
	
	//LOGIN USER
	public Optional<User> login(String email, String password)
	{
		Optional<User> u = repo.findByEmail(email);
		if(u.isPresent() && u.get().getPassword().equals(password))
		{
			return u;
		}
		
		return Optional.empty();
	}

}
