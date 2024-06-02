package com.shop.controller.admin;

import com.shop.model.Category;
import com.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/category")
    public String index(Model model){
        List<Category> listCategory = this.categoryService.getAll();
        model.addAttribute("listCategory",listCategory);
        return "admin/category/index";
    }
    @GetMapping("/category-add")
    public String add(Model model){
        Category category = new Category();
        category.setCategoryStatus(true);
        model.addAttribute("category" ,category);
        return "admin/category/add";
    }
    @PostMapping("/category-add")
    public String saveAdd(@ModelAttribute("category") Category category){
        if(this.categoryService.create(category)){
            return "redirect:/admin/category";
        }else{
            return "redirect:/admin/category-add";
        }

    }
    @GetMapping("/category-edit")
    public String update(Model model, @RequestParam("id") int id){
        Category category = categoryService.findById(id);
        model.addAttribute("category" ,category);
        return "admin/category/edit";
    }
    @PostMapping("/category-edit")
    public String saveUpdate(@ModelAttribute("category") Category category){
        if(this.categoryService.update(category)){
            return "redirect:/admin/category";
        }else{
            return "redirect:/admin/category-edit";
        }

    }
    @GetMapping("/category-delete")
    public String detele(@RequestParam("id") int id){
        if(categoryService.delete(id)){
            return "redirect:/admin/category";
        }else{
            return "redirect:/admin/category";
        }

    }
}
