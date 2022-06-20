package com.example.aseca.services;

import com.example.aseca.models.Category;
import com.example.aseca.models.Product;
import com.example.aseca.models.dto.ProductFilterDto;
import com.example.aseca.repositories.CategoryRepository;
import com.example.aseca.repositories.ProductRepository;
import com.github.myzhan.locust4j.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
            return null;
        }
    }

    public AbstractTask getProductByIdTask(UUID itemId) throws Exception {
        return new AbstractTask() {
            @Override
            public int getWeight() {
                return 2;
            }

            @Override
            public String getName() {
                return "getProductById";
            }

            @Override
            public void execute() throws Exception {
                getProductById(itemId);
            }
        };
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Page<Product> getProductsByCategory(UUID id, int page, int size) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Could not find Category with given id"));
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllByCategoryId(category.getCatId(), pageable);
    }

    public Page<Product> getProductsByFilter(ProductFilterDto productFilterDto, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if(productFilterDto.getName() != null){
            if(productFilterDto.getCategoryId() != null){
                if(productFilterDto.getPrice() != null){
                    return productRepository.findAllByNameContainingAndCategoryIdAndPriceGreaterThanEqual(productFilterDto.getName(), productFilterDto.getCategoryId(), productFilterDto.getPrice(), pageable);
                } else {
                    return productRepository.findAllByNameContainingAndCategoryId(productFilterDto.getName(), productFilterDto.getCategoryId(), pageable);
                }
            } else {
                if(productFilterDto.getPrice() != null){
                    return productRepository.findAllByNameContainingAndPriceGreaterThanEqual(productFilterDto.getName(), productFilterDto.getPrice(), pageable);
                } else {
                    return productRepository.findAllByNameContaining(productFilterDto.getName(), pageable);
                }
            }
        } else {
            if(productFilterDto.getCategoryId() != null) {
                if (productFilterDto.getPrice() != null) {
                    return productRepository.findAllByCategoryIdAndPriceGreaterThanEqual(productFilterDto.getCategoryId(), productFilterDto.getPrice(), pageable);
                } else {
                    return productRepository.findAllByCategoryId(productFilterDto.getCategoryId(), pageable);
                }
            } else {
                if (productFilterDto.getPrice() != null) {
                    return productRepository.findAllByPriceGreaterThanEqual(productFilterDto.getPrice(), pageable);
                } else {
                    return productRepository.findAll(pageable);
                }
            }
        }
    }
}
