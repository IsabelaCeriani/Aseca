package com.example.aseca.unitTesting;

import com.example.aseca.models.Category;
import com.example.aseca.models.Product;
import com.example.aseca.repositories.CategoryRepository;
import com.example.aseca.repositories.ProductRepository;
import org.junit.After;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryUnitTesting {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    private Data data = new Data();

    private class Data {
        Category cat1;
        Category cat2;

        Product product1;
        Product product2;
        Product product3;
        Product product4;
        Product product5;
        Product product6;
        Product product7;
        Product product8;

        void setup() {
            cat1 = new Category("salud", "imageLink", 1, "Salud", null, "selected_image1", "unselected_image1");
            cat2 = new Category("alimentos", "imageLink", 2, "Alimentos", null, "selected_image2", "unselected_image2");
            cat1 = categoryRepository.save(cat1);
            cat2 = categoryRepository.save(cat2);

            product1 = new Product("Pollo", 22.5, "alimento rico");
            product2 = new Product("Pescado", 24, "alimento rico");
            product3 = new Product("Arroz", 10.5, "alimento rico");
            product4 = new Product("Leche", 8, "alimento rico");
            product5 = new Product("Agua", 4, "alimento rico");

            product6 = new Product("Protector Solar", 112, "salud");
            product7 = new Product("Bronceador", 80.5, "salud");
            product8 = new Product("Maquillaje", 200, "salud");

            product1 = productRepository.save(product1);
            product2 = productRepository.save(product2);
            product3 = productRepository.save(product3);
            product4 = productRepository.save(product4);
            product5 = productRepository.save(product5);
            product6 = productRepository.save(product6);
            product7 = productRepository.save(product7);
            product8 = productRepository.save(product8);
        }

        void rollback() {
            categoryRepository.deleteAll();
            productRepository.deleteAll();
        }
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

    @Test
    public void test001_getCategoryById() {
        Category category = categoryRepository.findById(data.cat1.getCatId()).get();
        assert category.getName().equals("Salud");
    }
}
