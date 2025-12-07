package com.music.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.music.song.entity.Song;
import com.music.song.service.SongService;

@RestController
@RequestMapping("/api/songs")
@CrossOrigin(origins="*")
public class SongController {
	
	@Autowired
	private SongService service;
	
	//List all the songs
	@GetMapping
	public List<Song> all()
	{
		return service.findAll();
	}
	 //Set visibility to the songs
	@GetMapping("/visible")
	public List<Song> visible()
	{
		return service.visible();
	}
	
	//Find song by id
	@GetMapping("/{id}")
	public Song get(@PathVariable Long id)
	{
		return service.findById(id).orElseThrow(() -> new RuntimeException("Song not found: "+ id));
	}
	
	//creating a song
	@PostMapping
	public Song create(@RequestBody Song s)
	{
		return service.save(s);
	}
	
	//update a song by id
	@PutMapping("/{id}")
	public Song update(@PathVariable Long id, @RequestBody Song s)
	{
		Song ex = service.findById(id).orElseThrow(() -> new RuntimeException("Song not found: "+ id));
		ex.setName(s.getName());
		ex.setSinger(s.getSinger());
		ex.setMusicDirector(s.getMusicDirector());
		ex.setAlbumName(s.getAlbumName());
		ex.setReleaseDate(s.getReleaseDate());
		ex.setVisible(s.getVisible());
		return service.save(ex);
		
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id)
	{
		service.delete(id);
		return "deleted";
	}
	
	@GetMapping("/search")
	public List<Song> search (@RequestParam String q)
	{
		return service.searchByName(q);
	}
	
	@PutMapping("/{id}/visibility")
	public Song setVisibility(@PathVariable Long id, @RequestParam boolean visible)
	{
		
		Song s = service.findById(id).orElseThrow(() -> new RuntimeException("Song not found: "+ id));
		s.setVisible(visible);
		return service.save(s);
	}
	
}
