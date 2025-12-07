package com.music.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.user.entity.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
	
	List<Playlist> findByUserId(int userId);

}
