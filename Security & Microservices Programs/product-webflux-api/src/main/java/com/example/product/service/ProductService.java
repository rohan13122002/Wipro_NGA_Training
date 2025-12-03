package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final Validator validator;

    public ProductService(ProductRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Flux<Product> getAll() {
        return repository.findAll();
    }

    public Mono<Product> getById(String id) {
        return repository.findById(id);
    }

    public Mono<Product> create(Product product) {
        return validate(product)
                .map(p -> {
                    p.setId(null);
                    p.setCreatedAt(Instant.now());
                    p.setUpdatedAt(Instant.now());
                    return p;
                })
                .flatMap(repository::save);
    }

    public Mono<Product> update(String id, Product product) {
        return validate(product)
                .flatMap(payload -> repository.findById(id)
                        .flatMap(existing -> {
                            existing.setName(payload.getName());
                            existing.setDescription(payload.getDescription());
                            existing.setPrice(payload.getPrice());
                            existing.setUpdatedAt(Instant.now());
                            return repository.save(existing);
                        }));
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    public Flux<Product> findByPriceRange(BigDecimal min, BigDecimal max) {
        return repository.findByPriceBetween(min, max);
    }

    private Mono<Product> validate(Product product) {
        var violations = validator.validate(product);
        if (!violations.isEmpty()) {
            String msg = violations.stream()
                    .map(v -> v.getPropertyPath() + " " + v.getMessage())
                    .reduce((a, b) -> a + "; " + b)
                    .orElse("Validation error");
            return Mono.error(new IllegalArgumentException(msg));
        }
        return Mono.just(product);
    }
}

