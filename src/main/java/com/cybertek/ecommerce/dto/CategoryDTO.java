package com.cybertek.ecommerce.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private Integer id;

    private String name;

    private String description;
}