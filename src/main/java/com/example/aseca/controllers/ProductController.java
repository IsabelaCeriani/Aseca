package com.example.aseca.controllers;

import com.example.aseca.models.Product;
import com.example.aseca.models.dto.ProductFilterDto;
import com.example.aseca.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/item")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/get_products/{categoryId}")
    private Page<Product> getProductsByCategory(@PathVariable("categoryId") UUID id,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size){
        return productService.getProductsByCategory(id, page, size);
    }

    @GetMapping("/get/{itemId}")
    public ResponseEntity<Product> getProductById(@PathVariable("itemId") UUID itemId) throws Exception {
        return ResponseEntity.ok(productService.getProductById(itemId));
    }

    @PostMapping("/filter")
    public Page<Product> getProductsByFilter(@RequestBody ProductFilterDto filter,
                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size) {
        return productService.getProductsByFilter(filter, page, size);
    }

}
