package com.example.aseca.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

    private UUID catId;

    private String display_name;

    private String name;

    private UUID parent_catId;

}
