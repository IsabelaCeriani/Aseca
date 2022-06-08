package com.example.aseca.repositories;

import com.example.aseca.models.Category;
import com.example.aseca.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.productCategories = ?1")
    List<Product> findAllByCategory(List<Category> productCategories);






}
