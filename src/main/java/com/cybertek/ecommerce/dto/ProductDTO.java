package com.cybertek.ecommerce.dto;

import com.cybertek.ecommerce.entity.Category;
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

}