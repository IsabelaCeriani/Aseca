package com.example.aseca.services;

import com.example.aseca.models.Category;
import com.example.aseca.models.Product;
import com.example.aseca.repositories.CategoryRepository;
import com.example.aseca.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product getProductById(UUID itemId) throws Exception {
        Optional<Product> product = productRepository.findById(itemId);
        if(product.isPresent()){
            return product.get();
        } else {
            throw new NoSuchFieldException("Item does not exist");
        }
    }

    public List<Product> getProductsByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Could not find Category with given id"));
        return productRepository.findAllByCategory(List.of(category));
    }
}
