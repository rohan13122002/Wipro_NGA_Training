package com.example.product.repository;

import com.example.product.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    // Custom query for price range
    Flux<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
}

