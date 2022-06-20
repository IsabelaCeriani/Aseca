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
public class Category {

    @Id
    @GeneratedValue
    private UUID catId;

    @Column
    private String display_name;

    @Column
    private String image;

    @Column
    private int level;

    @Column
    private String name;

    @Column
    private UUID parent_catId;

    @Column
    private String selected_image;

    @Column
    private String unselected_image;

    public Category(String display_name, String image, int level, String name, UUID parent_catId, String selected_image, String unselected_image){
        this.display_name = display_name;
        this.image = image;
        this.level = level;
        this.name = name;
        this.parent_catId = parent_catId;
        this.selected_image = selected_image;
        this.unselected_image = unselected_image;
    }


}
