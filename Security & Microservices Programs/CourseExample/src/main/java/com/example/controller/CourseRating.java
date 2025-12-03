package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class CourseRating {
	
		@GetMapping("/{courseId}")
	    public ResponseEntity<Map<String, Object>> getRating(@PathVariable String courseId) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("courseId", courseId);
	        response.put("ratings", 4.5);
	        response.put("comments", List.of("Great course!", "Loved the examples"));
	        return ResponseEntity.ok(response);
	    }
	}



