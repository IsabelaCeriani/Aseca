package com.example.aseca.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private String description;

    @ManyToMany
//    @JoinTable(
//            name = "pruduct_category",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> productCategories;

    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public List<Category> addCategory(Category category){
        this.productCategories.add(category);
        return this.productCategories;
    }
}
