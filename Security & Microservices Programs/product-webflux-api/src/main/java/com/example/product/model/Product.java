package com.example.product.model;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

	import java.math.BigDecimal;
	import java.time.Instant;

	@Data
	@Document(collection = "products")
	public class Product {

	    @Id
	    private String id;

	    @NotBlank
	    @Size(max = 120)
	    private String name;

	    @Size(max = 500)
	    private String description;

	    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be non-negative")
	    private BigDecimal price;

	    private Instant createdAt;
	    private Instant updatedAt;
	}



