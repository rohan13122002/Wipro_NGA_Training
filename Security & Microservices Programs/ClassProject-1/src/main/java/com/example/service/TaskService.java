package com.example.service;


import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository tasks;
    private final UserRepository users;

    public TaskService(TaskRepository tasks, UserRepository users) {
        this.tasks = tasks;
        this.users = users;
    }


    // TEAM_LEAD allocation
    public Task allocate(Long taskId, String assigneeUsername) {
        Task t = tasks.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        User assignee = users.findByUsername(assigneeUsername)
                .orElseThrow(() -> new IllegalArgumentException("Assignee not found"));
        t.setAssignee(assignee);
        return tasks.save(t);
    }

    // TEAM_LEAD update (full)
    public Task update(Long id, String title, String description, String status) {
        Task t = tasks.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        if (title != null) t.setTitle(title);
        if (description != null) t.setDescription(description);
        if (status != null) t.setStatus(status);
        return tasks.save(t);
    }

    // TEAM_LEAD delete
    public void delete(Long id) {
        tasks.deleteById(id);
    }

    // SD + TEAM_LEAD: list
    public List<Task> listAll() {
        return tasks.findAll();
    }

    // SD: own tasks
    public List<Task> listForUser(User user) {
        return tasks.findByAssignee(user);
    }

    
}

