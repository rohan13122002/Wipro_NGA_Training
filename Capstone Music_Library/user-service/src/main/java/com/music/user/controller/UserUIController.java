package com.music.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.music.user.entity.Playlist;
import com.music.user.entity.User;
import com.music.user.service.PlaylistService;
import com.music.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserUIController {
	
	private final UserService userService;
	private final PlaylistService playlistService;
	private final RestTemplate rest = new RestTemplate();
	
	@Value("${song.service.url:http://localhost:8082}")
	private String songServiceUrl;
	
	//CONSTRUCTOR DEPENDENCY INJECTION
	public UserUIController(UserService userService, PlaylistService playlistService)
	{
		this.userService= userService;
		this.playlistService=playlistService;
	}
	
	//LOGIN PAGE
	@GetMapping({"/","/user/login"})
	public String loginPage()
	{
		return "login";
	}
	
	//LOGIN PAGE
	@PostMapping("/user/login")
	public String doLogin(@RequestParam String email, @RequestParam String password, Model m, HttpSession session)
	{
		var u = userService.login(email, password);
		
		if(u.isPresent())
		{
			session.setAttribute("userId", u.get().getId());
			session.setAttribute("username",u.get().getName());
			return "redirect:/user/home";
		}
		
		m.addAttribute("error", "Invalid credentials");
		return "login";
	}
	
	//REGISTER PAGE
	@GetMapping("/user/register")
	public String registerPage()
	{
		return "register";
	}
	
	//REGISTER
	@PostMapping("/user/register")
	public String doRegister(@ModelAttribute User user, Model m)
	{
		userService.register(user);
		m.addAttribute("message","Registered successfully, Please login");
		return "login";
	}
	
	//HOME PAGE
	@GetMapping("/user/home")
	public String homePage(Model m, HttpSession session)
	{
		m.addAttribute("username", session.getAttribute("username"));
		return "user-home";
	}
	
	//SEARCH SONG
	@GetMapping("/user/search")
	public String searchPage()
	{
		return "search";
	}
	
	//SEARCH SONGS BY RESULT
	@GetMapping("/user/search/results")
	public String searchSongs(@RequestParam String q, Model m)
	{
			String url = songServiceUrl + "/api/songs/search?q=" + q;
			var songs = rest.getForObject(url, Object[].class);
			m.addAttribute("songs", songs);
		
		    return "search";
	}
	
	//PLAYLIST PAGES
	@GetMapping("/user/playlists")
	public String viewPlaylists(HttpSession session, Model m)
	{
		int userId = (int) session.getAttribute("userId");
		List<Playlist> list = playlistService.getPlaylists(userId);
		m.addAttribute("playlists",list);
		return "playlist-view";
		
	}
	
	//CREATE PLAYLIST
	@GetMapping("/user/playlists/create")
	public String createPlayistPage()
	{
		return "playlist-create";
	}
	
	//CREATE PLAYLIST
	@PostMapping("user/playlists/create")
	public String createPlaylist(@RequestParam String name, HttpSession session)
	{
		int userId = (int) session.getAttribute("userId");
		playlistService.createPlaylist(userId, name);
		return "redirect:/user/playlists";
	}
	
	//ADD SONG TO PLAYLIST BY ID
	@PostMapping("/user/playlists/{songId}/add")
	public String addSong(@PathVariable int id, @RequestParam Long songId)
	{
		playlistService.addSong(id, songId);
		return "redirect:/user/playlists";
	}
	
	//LOGOUT
	@GetMapping("/user/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "redirect:/user/login";
	}
	
	
}
