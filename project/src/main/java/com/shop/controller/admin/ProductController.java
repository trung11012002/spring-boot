package com.shop.controller.admin;

import com.shop.model.Category;
import com.shop.model.Product;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;
import com.shop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller(value = "productControllerOfAdmin")
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private StorageService storageService;

    @RequestMapping("/product")
    public String index(Model model) {
        List<Product> listProduct = productService.getAll();
        model.addAttribute("listProduct", listProduct);
        return "admin/product/index";
    }

    @GetMapping("/product-add")
    public String add(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        List<Category> listCategory = categoryService.getAll();
        model.addAttribute("listCategory", listCategory);
        return "admin/product/add";
    }

    @PostMapping("/product-add")
    public String addSave(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file) {
        String fileName = storageService.store(file);
        product.setImage(fileName);
        if (productService.create(product)) {
            return "redirect:/admin/product";
        } else {
            return "redirect:/admin/product-add";
        }

    }

    @GetMapping("/product-delete")
    public String delete(@RequestParam("id") Long id) {
        storageService.delete(id);
        if (productService.delete(id)) {
            return "redirect:/admin/product";
        } else {
            return "redirect:/admin/product";
        }

    }

    @GetMapping("/product-update")
    public String update(Model model, @RequestParam("id") Long id) {
        Product product = productService.find(id);
        model.addAttribute("product", product);
        List<Category> listCategory = categoryService.getAll();
        model.addAttribute("listCategory", listCategory);
        return "admin/product/edit";
    }

    @PostMapping("/product-update")
    public String updateSave(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = storageService.store(file);
            product.setImage(fileName);
            storageService.delete(product.getId());
        }
        if(productService.update(product)){
            return "redirect:/admin/product";
        }else {
            return "redirect:/admin/product?id=" + product.getId();
        }
    }

}
