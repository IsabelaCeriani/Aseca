package com.example.aseca.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue
    private Long catId;

    @Column
    private String display_name;

    @Column
    private String image;

    @Column
    private int level;

    @Column
    private String name;

    @Column
    private Long parent_catId;

    @Column
    private String selected_image;

    @Column
    private String unselected_image;
//
    @ManyToMany(mappedBy = "productCategories")
    private List<Product> productsInCategory;

    public Category(String display_name, String image, int level, String name, Long parent_catId, String selected_image, String unselected_image){
        this.display_name = display_name;
        this.image = image;
        this.level = level;
        this.name = name;
        this.parent_catId = parent_catId;
        this.selected_image = selected_image;
        this.unselected_image = unselected_image;
    }


    public Category(Long catId, String display_name, String image, int level, String name, Long parent_catId, String selected_image, String unselected_image) {
        this.catId = catId;
        this.display_name = display_name;
        this.image = image;
        this.level = level;
        this.name = name;
        this.parent_catId = parent_catId;
        this.selected_image = selected_image;
        this.unselected_image = unselected_image;
    }
}
