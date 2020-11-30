package com.cybertek.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer quantity;

    private String description;

    private CategoryDTO categoryDTO;

    private Integer  categoryId;

    public ProductDTO(Integer id, String name, BigDecimal price, Integer quantity, String description, CategoryDTO categoryDTO) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.categoryDTO = categoryDTO;
    }
}