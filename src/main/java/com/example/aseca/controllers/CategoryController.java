package com.example.aseca.controllers;

import com.example.aseca.models.Category;
import com.example.aseca.models.Product;
import com.example.aseca.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get_category/{id}")
    private ResponseEntity<Category> getCategory(@PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.getCategory(id));
    }



}
