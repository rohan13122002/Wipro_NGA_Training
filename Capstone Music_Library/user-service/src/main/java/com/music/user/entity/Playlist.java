package com.music.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="playlist")
//creates a table with name playlist
public class Playlist {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private int userId;
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String songIds;    // store like "1, 5, 6"

}
