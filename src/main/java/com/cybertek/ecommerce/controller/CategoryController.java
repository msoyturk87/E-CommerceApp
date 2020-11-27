package com.cybertek.ecommerce.controller;



import com.cybertek.ecommerce.dto.CategoryDTO;
import com.cybertek.ecommerce.service.CategoryService;
import com.cybertek.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;



    @GetMapping("/categories")
    public String showCategories(Model model) {
/*

        System.out.println("productService.readAll().toString() = " + productService.readAll().get(0).getDescription());
        System.out.println("productService.readAll().toString() = " + productService.readAll().get(0).getName());
        System.out.println("productService.readAll().toString() = " + productService.readAll().get(0).getCategoryDTO().getName());
*/

        model.addAttribute("categories",categoryService.readAll());
        return "/category/categories";
    }

    @GetMapping("/create")
    public String addCategory(Model model) {

        model.addAttribute("category",new CategoryDTO());

        return "/category/create";
    }

    @PostMapping("/categories")
    public String addCategory(@ModelAttribute("category") CategoryDTO categoryDTO,Model model) {
        categoryService.create(categoryDTO);

        return "redirect:/category/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/category/categories";
    }

}





















