package com.example.aseca.unitTesting;

import com.example.aseca.AsecaApplication;
import com.example.aseca.models.Category;
import com.example.aseca.models.Product;
import com.example.aseca.repositories.CategoryRepository;
import com.example.aseca.repositories.ProductRepository;
import com.example.aseca.services.CategoryService;
import com.example.aseca.services.ProductService;
import org.junit.After;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AsecaApplication.class)
@WebAppConfiguration
public class CategoryUnitTesting {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void test001_getCategoryById() {
        Category cat = categoryRepository.findAll().get(80);
        Category category = categoryService.getCategory(cat.getCatId());
        assert category.getName().equals("Salud");
    }

    @Test
    public void test002_getNonExistentCategoryById() {
        UUID id = UUID.randomUUID();
        Category category = categoryService.getCategory(id);
        assertNull(category);
    }
}
