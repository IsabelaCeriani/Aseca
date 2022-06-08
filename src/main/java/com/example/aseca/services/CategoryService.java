package com.example.aseca.services;

import com.example.aseca.models.Category;
import com.example.aseca.models.Product;
import com.example.aseca.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No category found with given id"));
    }

}
