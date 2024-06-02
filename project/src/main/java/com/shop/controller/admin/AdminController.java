package com.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping
    public String index(){
        return "redirect:/admin/";
    }
    @RequestMapping("/")
    public String admin(){
        return "admin/index";
    }
}
