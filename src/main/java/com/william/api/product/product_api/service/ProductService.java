package com.william.api.product.product_api.service;

import com.william.api.product.product_api.models.Product;
import com.william.api.product.product_api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> 
            new IllegalArgumentException("Produto com ID " + id + " não encontrado"));
    }

    public Product saveProduct(Product product) {
        if (product.getCategory() != null) {
            product.setCategory_id(product.getCategory().getId());
        }
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Produto com ID " + id + " não encontrado"));
    
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
    
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getAllProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    public List<Product> getProductsByCategoryId(String categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Query("{ 'product_identifier': ?0 }")
    public List<Product> findByCategoryId(String categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Product findProductByIdentifier(String productIdentifier) {
        return productRepository.findAll().stream()
                .filter(product -> productIdentifier.equals(product.getProduct_identifier()))
                .findFirst()
                .orElseThrow(() -> 
                    new IllegalArgumentException("Produto com identificador " + productIdentifier + " not found"));
    }
}
