package com.example.dto;


import com.example.model.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

	@Getter @Setter
	public class RegisterRequest {
	    @NotBlank
	    private String username;
	    @NotBlank
	    private String password;
	    @NotNull
	    private Role role; // TEAM_LEAD or SD
	}



