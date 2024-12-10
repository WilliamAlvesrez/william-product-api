package com.william.api.product.product_api.service;

import com.william.api.product.product_api.models.Category;
import com.william.api.product.product_api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(String id, Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            category.setId(id);
            return categoryRepository.save(category);
        } else {
            throw new IllegalArgumentException("Categoria com ID " + id + " não encontrado.");
        }
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    public Page<Category> getAllCategoriesPageable(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category findCategoryById(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> 
            new IllegalArgumentException("Categoria com ID " + id + " não encontrada"));
    }
}
