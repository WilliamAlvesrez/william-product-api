package com.william.api.product.product_api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.william.api.product.product_api.models.Product;

import org.springframework.data.mongodb.repository.Query;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{ 'category_id': ?0 }")
    List<Product> findByCategoryId(String categoryId);
}
