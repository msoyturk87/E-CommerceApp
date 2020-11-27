package com.cybertek.ecommerce.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer quantity;

    private String description;

    private Category category;

}
