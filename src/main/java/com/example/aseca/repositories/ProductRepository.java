package com.example.aseca.repositories;

import com.example.aseca.models.Category;
import com.example.aseca.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findAllByCategoryId(UUID categoryId, Pageable pageable);

    Page<Product> findAllByNameContainingAndCategoryIdAndPriceGreaterThanEqual(String name, UUID categoryId, double price, Pageable pageable);

    Page<Product> findAllByNameContainingAndCategoryId(String name, UUID categoryId, Pageable pageable);

    Page<Product> findAllByNameContainingAndPriceGreaterThanEqual(String name, Double price, Pageable pageable);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    Page<Product> findAllByCategoryIdAndPriceGreaterThanEqual(UUID categoryId, Double price, Pageable pageable);

    Page<Product> findAllByPriceGreaterThanEqual(Double price, Pageable pageable);
}
