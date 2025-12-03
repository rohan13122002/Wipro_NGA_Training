package com.example.controller;


	import com.example.model.Role;
	import com.example.model.Task;
	import com.example.model.User;
	import com.example.service.TaskService;
	import com.example.service.UserService;
	import org.springframework.security.access.prepost.PreAuthorize;
	import org.springframework.security.core.Authentication;
	import org.springframework.security.core.context.SecurityContextHolder;
	import org.springframework.web.bind.annotation.*;

	import jakarta.validation.Valid;
	import java.util.List;

	@RestController
	@RequestMapping("/api/user/tasks")
	public class TaskController {

	    private final TaskService tasks;
	    private final UserService users;

	    public TaskController(TaskService tasks, UserService users) {
	        this.tasks = tasks;
	        this.users = users;
	    }


	    // TEAM_LEAD: allocate to user
	    @PostMapping("/{id}/allocate/{assigneeUsername}")
	    @PreAuthorize("hasRole('TEAM_LEAD')")
	    public Task allocate(@PathVariable Long id, @PathVariable String assigneeUsername) {
	        return tasks.allocate(id, assigneeUsername);
	    }

	    // TEAM_LEAD: update (full)
	    @PutMapping("/{id}")
	    @PreAuthorize("hasRole('TEAM_LEAD')")
	    public Task update(@PathVariable Long id,
	                       @RequestParam(required = false) String title,
	                       @RequestParam(required = false) String description,
	                       @RequestParam(required = false) String status) {
	        return tasks.update(id, title, description, status);
	    }

	    // TEAM_LEAD: delete
	    @DeleteMapping("/{id}")
	    @PreAuthorize("hasRole('TEAM_LEAD')")
	    public void delete(@PathVariable Long id) {
	        tasks.delete(id);
	    }

	   

	    // SD or TEAM_LEAD: list view aligns with dashboard; still providing endpoints
	    @GetMapping
	    public List<Task> list() {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String username = (String) auth.getPrincipal();
	        User u = users.getByUsername(username);
	        return u.getRole() == Role.TEAM_LEAD ? tasks.listAll() : tasks.listForUser(u);
	    }
	}

	
	


