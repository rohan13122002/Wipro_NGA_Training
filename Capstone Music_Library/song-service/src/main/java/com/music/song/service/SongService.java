package com.music.song.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.song.entity.Song;
import com.music.song.repository.SongRepository;

@Service
public class SongService {
	
	@Autowired
	private SongRepository repo;
	
	//saves the song
	public Song save(Song s)
	{
		return repo.save(s);
	}
	
	//returns a list of song
	public List<Song> findAll()
	{
		return repo.findAll();
	}
	
	//returns a song found by id
	public Optional<Song> findById(Long id)
	{
		return repo.findById(id);
	}
	
	//deletes a song by id
	public void delete(Long id)
	{
		repo.deleteById(id);
	}
	
	//search song by name
	public List<Song> searchByName(String q)
	{
		return repo.findByNameContainingIgnoreCase(q);
	}
	
	//set visibility to song
	public List<Song> visible()
	{
		return repo.findByVisibleTrue();
	}

}
