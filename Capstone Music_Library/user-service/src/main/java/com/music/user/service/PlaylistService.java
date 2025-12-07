package com.music.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.music.user.entity.Playlist;
import com.music.user.repository.PlaylistRepository;

@Service
public class PlaylistService {
	
	private final PlaylistRepository repo;
	private final RestTemplate rest= new RestTemplate();
	
	@Value("${song.service.url}")
	private String songServiceUrl;
	
	//constructor dependency injection
	public PlaylistService(PlaylistRepository repo)
	{
		this.repo= repo;
	}
	
	//return saved playlist with name & userId
	public Playlist createPlaylist(int userId, String name)
	{
		Playlist p = new Playlist();
		p.setUserId(userId);
		p.setName(name);
		p.setSongIds("");
		return repo.save(p);
	}
	
	//return user by id
	public List<Playlist> getPlaylists(int userId) 
	{
		return repo.findByUserId(userId);
	}
	
	//return save song after adding song to playlist
	public Playlist addSong(int playlistId, Long songId)
	{
		Playlist p = repo.findById(playlistId).orElseThrow();
		String ids = p.getSongIds();
		
		if(ids == null || ids.isEmpty())
		{
			ids = songId.toString();
		}
		else
		{
			ids = ids + "," + songId;
		}
		p.setSongIds(ids);
		return repo.save(p);
	}

}
