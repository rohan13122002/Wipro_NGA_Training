package com.example.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BeanController {
	
	
	@GetMapping("/get")
	public ResponseEntity<String> getEmployee()
	{
		//returning a ResponseEntity with a body and status code.
		return new ResponseEntity<>("The employee details retrieved",HttpStatus.OK);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<String> getEmployeeById()
	{
		
		ResponseEntity responseEntity = null;
		if(responseEntity != null)
		//returning a ResponseEntity with a body and status code.
		return new ResponseEntity<>("The employee details retrieved",HttpStatus.OK);
		
		else
			 return new ResponseEntity<>("An internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
	}
	@PostMapping("/post/create")
	public  ResponseEntity<String> postEmployee()
	{
		
		return new ResponseEntity<>("The employee details retrieved",HttpStatus.CREATED);
		
	}
	@PutMapping("/put")
	public  ResponseEntity<String> updateEmployee()
	{
		
		return new ResponseEntity<>("The employee details retrieved",HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public  ResponseEntity<String> deleteEmployee()
	{
		return new ResponseEntity<>("The employee details retrieved",HttpStatus.OK);
		
	}
}


