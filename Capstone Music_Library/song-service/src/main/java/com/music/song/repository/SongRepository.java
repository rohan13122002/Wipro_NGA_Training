package com.music.song.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.song.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
	
	List<Song> findByNameContainingIgnoreCase(String name);
	List<Song> findBySingerContainingIgnoreCase(String singer);
	List<Song> findByMusicDirectorContainingIgnoreCase(String musicDirector);
	List<Song> findByAlbumNameContainingIgnoreCase(String albumName);
	
	List<Song> findByVisibleTrue();

}
