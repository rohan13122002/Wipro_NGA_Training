package com.music.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.music.admin.entity.Admin;
import com.music.admin.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminUIController {
	
	
    private final AdminService adminService;
    private final RestTemplate rest = new RestTemplate();

    @Value("${song.service.url:http://localhost:8082}")
    private String songServiceUrl;
    
    public AdminUIController(AdminService adminService) {
        this.adminService = adminService;
    }

    // show login page (GET)
    @GetMapping({"/", "/login", "/admin/login"})
    public String loginPage(Model m) {
        if (!m.containsAttribute("error")) m.addAttribute("error", "");
        if (!m.containsAttribute("message")) m.addAttribute("message", "");
        return "login";
    }

    // handle login form (POST) -- sets session attribute on success
    @PostMapping({"/doLogin", "/login", "/admin/login"})
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          Model m,
                          HttpSession session) {

        var opt = adminService.findByEmail(email);
        if (opt.isPresent() && opt.get().getPassword().equals(password)) {
            // store minimal session marker
            session.setAttribute("adminEmail", email);
            return "redirect:/admin/home";
        }

        m.addAttribute("error", "Invalid Credentials");
        return "login";
    }

    // registration pages
    @GetMapping({"/register", "/admin/register"})
    public String registerPage(Model m) {
        if (!m.containsAttribute("error")) m.addAttribute("error", "");
        return "register";
    }
    
    //admin register page
    @PostMapping({"/register", "/admin/register"})
    public String doRegister(@ModelAttribute Admin admin, Model m) {
        adminService.register(admin);
        m.addAttribute("message", "Registered successfully. Please login");
        return "login";
    }

    // logout - invalidate session and redirect to login
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // helper: ensure admin logged in; if not redirect to login
    private boolean isLoggedIn(HttpSession session) {
        return session != null && session.getAttribute("adminEmail") != null;
    }

    // admin home (protected)
    @GetMapping({"/admin/home"})
    public String adminHome(HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/login";
        return "admin-home";
    }
    
    //admin adds song
    @GetMapping("/admin/add-song")
    public String addSongPage(HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/login";
        return "add-song";
    }

    //admin views the song
    @GetMapping("/admin/view-songs")
    public String viewSongsPage(HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/login";
        return "view-songs";
    }

    //admin updates the song
    @GetMapping("/admin/update-song")
    public String updateSongPage(HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/login";
        return "update-song";
    }

    //admin deletes the song
    @GetMapping("/admin/delete-song")
    public String deleteSongPage(HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/login";
        return "delete-song";
    }

    // proxy to song service to create songs from UI
    @PostMapping("/admin/proxy/songs")
    @ResponseBody
    public Object proxyCreateSong(@org.springframework.web.bind.annotation.RequestBody Object payload, HttpSession session) {
        if (!isLoggedIn(session)) {
            return java.util.Map.of("error", "Not logged in");
        }
        return rest.postForObject(songServiceUrl + "/api/songs", payload, Object.class);
    }
}
