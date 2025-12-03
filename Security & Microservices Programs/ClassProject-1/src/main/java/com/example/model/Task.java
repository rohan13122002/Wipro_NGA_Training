package com.example.model;

import jakarta.persistence.*;
import lombok.*;

	@Entity
	@Table(name = "tasks")
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
	public class Task {
	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String title;

	    private String description;

	    @Column(nullable = false)
	    private String status; // e.g., NEW, IN_PROGRESS, DONE

	    // Allocation to a user
	    @ManyToOne
	    @JoinColumn(name = "assignee_id")
	    private User assignee;
	}




