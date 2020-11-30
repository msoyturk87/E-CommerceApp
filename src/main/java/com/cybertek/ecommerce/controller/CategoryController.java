package com.cybertek.ecommerce.controller;



import com.cybertek.ecommerce.dto.CategoryDTO;
import com.cybertek.ecommerce.implementation.CategoryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {


    private CategoryDao categoryDao;


    @Autowired
    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;

    }





    @GetMapping("/categories")
    public String showCategories(Model model) {


        model.addAttribute("categories", categoryDao.readAll());
        return "/category/categories";
    }

    @GetMapping("/create")
    public String addCategory(Model model) {
        model.addAttribute("category", new CategoryDTO());

        return "/category/create";
    }

    @PostMapping("/categories")
    public String addCategory(@ModelAttribute("category") CategoryDTO categoryDTO) {
        categoryDao.create(categoryDTO);

        return "redirect:/category/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryDao.delete(id);
        return "redirect:/category/categories";
    }

    @GetMapping("/detail/{id}")
    public String detailCategory(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("category", categoryDao.readById(id));
        return "/category/detail";
    }

    @PostMapping("/detail/{id}")
    public String updateCategory(@ModelAttribute("category") CategoryDTO category,@PathVariable("id") Integer id,Model model) {
        categoryDao.update(category,id);


        return "redirect:/category/categories";

    }
}




















