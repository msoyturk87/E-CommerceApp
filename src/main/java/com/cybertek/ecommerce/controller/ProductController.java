package com.cybertek.ecommerce.controller;

import com.cybertek.ecommerce.dto.ProductDTO;
import com.cybertek.ecommerce.implementation.CategoryDao;
import com.cybertek.ecommerce.implementation.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/product")
public class ProductController {


    private CategoryDao categoryDao;
    private ProductDao productDao;


    @Autowired
    public ProductController(CategoryDao categoryDao,ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao= productDao;


    }

    @GetMapping("/create")
    public String createProduct(Model model) {

        model.addAttribute("product", new ProductDTO());
        model.addAttribute("categories",categoryDao.readAll());
        return "/product/create";
    }

    @GetMapping("/products")
    public String showProducts(Model model) {

        model.addAttribute("products",productDao.readAll());
        return "/product/products";
    }

    @PostMapping("/products")
    public String addProduct(@ModelAttribute("product") ProductDTO productDTO) {
        productDTO.setCategoryDTO(categoryDao.readById(productDTO.getCategoryId()));
        productDao.create(productDTO);


        return "redirect:/product/products";
    }

    @GetMapping("/detail/{id}")
    public String detailProducts(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("product",productDao.readById(id));
        model.addAttribute("categories",categoryDao.readAll());
        return "/product/detail";
    }
    @PostMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("product",productDao.readById(id));
        model.addAttribute("categories",categoryDao.readAll());
        return "/product/update";
    }

    @PostMapping("/update/{id}")
    public String updateProducts(@PathVariable("id") Integer id,@ModelAttribute("product") ProductDTO productDTO,Model model) {
            productDao.update(productDTO,id);
        return "redirect:/product/products";
    }


}