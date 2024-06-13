package com.shop.controller.web;

import com.shop.model.Category;
import com.shop.model.Product;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("")
    public String home(Model model,@Param("categoryId") Long categoryId, @Param("keyword") String keyword, @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo) {
        Page<Product> listProduct = productService.getAll(pageNo);
        if(keyword != null){
            listProduct = productService.searchProduct(keyword,pageNo);
            model.addAttribute("keyword" ,keyword);
        }
        if(categoryId != null){
            listProduct = productService.searchProductByCategory(categoryId,pageNo);
            model.addAttribute("categoryId" ,categoryId);
        }
        int toltal = listProduct.getTotalPages();
        model.addAttribute("listCategory",categoryService.getAll());
        model.addAttribute("totalPage",listProduct.getTotalPages());
        model.addAttribute("currentPage" ,pageNo);
        model.addAttribute("listProduct",listProduct);
        return "index";
    }
}
