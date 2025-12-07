package com.music.admin.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.music.admin.entity.Admin;
import com.music.admin.repository.AdminRepository;

@Service
public class AdminService {
	
	private final AdminRepository repo;
	
	//constructor dependency injection
	public AdminService(AdminRepository repo)
	{
		this.repo = repo;
	}
	
	//saves the Admin
	public Admin register(Admin a)
	{
		return repo.save(a);
	}
	
	//find admin by email
	public Optional<Admin> findByEmail(String email)
	{
		return repo.findByEmail(email);
	}
	
	
}
