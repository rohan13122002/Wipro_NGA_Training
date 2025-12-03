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
@RequestMapping("/enrolled")
public class EnrollerCourse {

	    @GetMapping("/{userId}")
	    public ResponseEntity<Map<String, Object>> getCourses(@PathVariable String userId) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("userId", userId);
	        response.put("courseIds", List.of("C101", "C102"));
	        return ResponseEntity.ok(response);
	    }
	}

