package com.example.aseca.unitTesting;

import com.example.aseca.AsecaApplication;
import com.example.aseca.models.Category;
import com.example.aseca.models.Product;
import com.example.aseca.models.dto.ProductFilterDto;
import com.example.aseca.repositories.CategoryRepository;
import com.example.aseca.repositories.ProductRepository;
import com.example.aseca.services.CategoryService;
import com.example.aseca.services.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AsecaApplication.class)
@WebAppConfiguration
public class ProductTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private void setup(){
        Category cat1 = new Category("salud", "imageLink", 1, "Salud", null, "selected_image1", "unselected_image1");
        Category cat2 = new Category("alimentos", "imageLink", 2, "Alimentos", null, "selected_image2", "unselected_image2");

//
//        cat1.setProducts(List.of(product1, product2));
//        cat2.setProducts(List.of(product3));

        cat1 = categoryRepository.save(cat1);
        cat2 = categoryRepository.save(cat2);

//        List<Category> categories = categoryRepository.findAll();
//        Category cat11 = categories.get(0);
//        Category cat22 = categories.get(1);

        Product product1 = new Product("Pollo", 22.5, "alimento rico");
        Product product2 = new Product("Pescado", 24, "alimento rico");
        Product product3 = new Product("Arroz", 10.5, "alimento rico");

        product1.setCategoryId(cat1.getCatId());
        product2.setCategoryId(cat1.getCatId());
        product3.setCategoryId(cat2.getCatId());

        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);
        product3 = productRepository.save(product3);
    }

    public void rollback(){

        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void test001_getAllProductsByCategoryShouldSucceed(){
        List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        Page<Product> actualOutput = productService.getProductsByCategory(products.get(products.size() -2).getCategoryId(), 0, 10);
        List<Product> expected = List.of(products.get(0), products.get(1));

        Assertions.assertEquals(expected.size(), actualOutput.toList().size());
        Assertions.assertEquals(expected.get(0).getName(), actualOutput.toList().get(0).getName());
    }

    @Test
    public void test002_getProductByIdShouldSucceed() throws Exception {
        UUID id = productRepository.findAll().get(0).getId();
        Product actualOutput = productService.getProductById(id);
        Product expected = productRepository.findAll().get(0);

        Assertions.assertEquals(expected.getName(), actualOutput.getName());
    }

    @Test
    public void test003_getProductByIdShouldFail() throws Exception {
        UUID id = UUID.randomUUID();
        Product actualOutput = productService.getProductById(id);

        Assertions.assertEquals(null, actualOutput);
    }

    @Test
    public void test004_getProductByProductFilterShouldFilter() {
        ProductFilterDto filter = new ProductFilterDto();
        filter.setCategoryId(categoryRepository.findAll().get(80).getCatId());
        filter.setName("Pollo");
        filter.setPrice(22.5);
        Page<Product> actualOutput = productService.getProductsByFilter(filter, 0, 10);

        Assertions.assertEquals(1, actualOutput.toList().size());
        Assertions.assertEquals("Pollo", actualOutput.toList().get(0).getName());
    }

    @Test
    public void test005_getProductByOneProductFilterShouldFilter() {
        ProductFilterDto filter = new ProductFilterDto();
        filter.setCategoryId(categoryRepository.findAll().get(80).getCatId());
        Page<Product> actualOutput = productService.getProductsByFilter(filter, 0, 10);

        Assertions.assertEquals(2, actualOutput.toList().size());
        Assertions.assertEquals("Pollo", actualOutput.toList().get(0).getName());
    }

    @Test
    public void test006_getProductByTwoProductFilterShouldFilter() {
        ProductFilterDto filter = new ProductFilterDto();
        filter.setCategoryId(categoryRepository.findAll().get(80).getCatId());
        filter.setName("Pollo");
        Page<Product> actualOutput = productService.getProductsByFilter(filter, 0, 10);

        Assertions.assertEquals(1, actualOutput.toList().size());
        Assertions.assertEquals("Pollo", actualOutput.toList().get(0).getName());
    }

    @Test
    public void test007_getProductByTwoProductFilterShouldFilter() {
        ProductFilterDto filter = new ProductFilterDto();
        filter.setName("Pollo");
        filter.setPrice(22.5);
        Page<Product> actualOutput = productService.getProductsByFilter(filter, 0, 10);

        Assertions.assertEquals(10, actualOutput.toList().size());
        Assertions.assertEquals("Pollo", actualOutput.toList().get(0).getName());
    }

    @Test
    public void test008_getProductByTwoProductFilterShouldFilter() {
        ProductFilterDto filter = new ProductFilterDto();
        filter.setName("Arroz");
        filter.setPrice(22.5);
        Page<Product> actualOutput = productService.getProductsByFilter(filter, 0, 10);

        Assertions.assertEquals(0, actualOutput.toList().size());
    }
}


