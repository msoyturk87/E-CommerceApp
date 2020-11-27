package com.cybertek.ecommerce.controller;

import com.cybertek.ecommerce.dto.CategoryDTO;
import com.cybertek.ecommerce.dto.ProductDTO;
import com.cybertek.ecommerce.service.CategoryService;
import com.cybertek.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductService productService;
    @Autowired
    ProductService categoryService;

    @GetMapping("/create")
    public String addProductForm(Model model) {

        model.addAttribute("product", new ProductDTO());
        model.addAttribute("categories",categoryService.readAll());
        return "/product/create";
    }

    @GetMapping("/products")
    public String showProducts(Model model) {

        model.addAttribute("products",productService.readAll());
        return "/product/products";
    }

    @PostMapping("/products")
    public String addProduct(@ModelAttribute("product") ProductDTO productDTO, Model model) {
        productService.create(productDTO);
        System.out.println("productDTO.getName() = " + productDTO.getName());

        return "redirect:/product/products";
    }


}