package com.shop.controller.web;

import com.shop.model.Product;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;
    @RequestMapping("")
    public String home(Model model) {
        List<Product> listProduct = productService.getAll();
        model.addAttribute("listProduct",listProduct);
        return "index";
    }
}
