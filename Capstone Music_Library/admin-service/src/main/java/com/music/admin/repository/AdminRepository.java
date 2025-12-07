package com.music.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.admin.entity.Admin;

//REPOSITORY imports JpaRepository
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	Optional<Admin> findByEmail(String email);

}
