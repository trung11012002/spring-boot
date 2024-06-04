package com.shop.controller.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestCart {
    @GetMapping("/test/cart")
    public String index(HttpServletResponse response){
        // Lưu lại giỏ hàng vào cookie
        Cookie cartCookie = new Cookie("cart", "1");
        cartCookie.setMaxAge(30 * 24 * 60 * 60);
        cartCookie.setPath("123");
        response.addCookie(cartCookie);
        return "index";
    }
}
