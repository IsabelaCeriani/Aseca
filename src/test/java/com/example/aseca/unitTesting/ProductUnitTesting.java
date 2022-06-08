package com.example.aseca.unitTesting;

import com.example.aseca.controllers.ProductController;
import com.example.aseca.models.Category;
import com.example.aseca.models.Product;
import com.example.aseca.repositories.CategoryRepository;
import com.example.aseca.repositories.ProductRepository;
import com.example.aseca.services.ProductService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductUnitTesting {

    @Autowired
    private ProductController productController;

    @Autowired
    public ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    private final Data data = new Data();

    private class Data {
        Category cat1;
        Category cat2;

        Product product1;
        Product product2;
        Product product3;

        void setup() {
            cat1 = new Category("salud", "imageLink", 1, "Salud", null, "selected_image1", "unselected_image1");
            cat2 = new Category("alimentos", "imageLink", 2, "Alimentos", null, "selected_image2", "unselected_image2");
            cat1 = categoryRepository.save(cat1);
            cat2 = categoryRepository.save(cat2);

            product1 = new Product("Pollo", 22.5, "alimento rico");
            product2 = new Product("Pescado", 24, "alimento rico");
            product3 = new Product("Arroz", 10.5, "alimento rico");

            product1.addCategory(cat1);
            product2.addCategory(cat1);

            product1 = productRepository.save(product1);
            product2 = productRepository.save(product2);
            product3 = productRepository.save(product3);
        }

        void rollback() {
            categoryRepository.deleteAll();
            productRepository.deleteAll();
        }
    }


    @Test
    public void getAllProductsByCategoryShouldSucceed(){
        Long id = data.cat1.getCatId();
        List<Product> actualOutput = productService.getProductsByCategory(id);
        List<Product> expected = List.of(data.product1, data.product2);

        Assertions.assertSame(expected, actualOutput);
    }



    // Executed before each test
    @Before
    public void setUpDB() {
        data.setup();
    }

    // Executed after each test
    @After
    public void rollBackDB() {
        data.rollback();
    }
}
