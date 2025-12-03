package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class CourseCatalog {
	
	    private final RestTemplate restTemplate = new RestTemplate();

	    @GetMapping("/{userId}")
	    public ResponseEntity<Map<String, Object>> getCatalog(@PathVariable String userId) {
	        // Call EnrolledCourse Service
	        String enrolledUrl = "http://localhost:8081/enrolled/" + userId;
	        Map enrolledResponse = restTemplate.getForObject(enrolledUrl, Map.class);

	        List<String> courseIds = (List<String>) enrolledResponse.get("courseIds");

	        // Call CourseRating Service for each courseId
	        List<Map<String, Object>> courseDetails = new ArrayList<>();
	        for (String courseId : courseIds) {
	            String ratingUrl = "http://localhost:8082/rating/" + courseId;
	            Map ratingResponse = restTemplate.getForObject(ratingUrl, Map.class);
	            courseDetails.add(ratingResponse);
	        }

	        // Aggregate response
	        Map<String, Object> response = new HashMap<>();
	        response.put("userId", userId);
	        response.put("courses", courseDetails);

	        return ResponseEntity.ok(response);
	    }
	}



