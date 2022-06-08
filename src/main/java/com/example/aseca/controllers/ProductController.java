package com.example.aseca.controllers;

import com.example.aseca.models.Product;
import com.example.aseca.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /*TODO:
    *  1. Paginacion
    *  2. Orden
    *  3. Filter type
    * */
    @GetMapping("get_products/{categoryId}")
    private ResponseEntity<List<Product>> getProductsByCategory(Long id){
        return ResponseEntity.ok(productService.getProductsByCategory(id));
    }
    @GetMapping("/get/{itemId}")
    public ResponseEntity<Product> getProductById(@PathVariable("itemId") UUID itemId) throws Exception {
        return ResponseEntity.ok(productService.getProductById(itemId));
    }
}
