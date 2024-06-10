package com.shop.controller.web;

import com.shop.model.Cart;
import com.shop.model.CartItem;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.service.CartItemService;
import com.shop.service.CartService;
import com.shop.util.SecurityUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller(value = "cartControllerOfWeb")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;
    @GetMapping("/cart")
    public String cart(Model model) {
        try{
            Cart cart = cartService.getCartNowByUserId();
            model.addAttribute("cart",cart);
            model.addAttribute("cartTotal" , cartService.cartTotal(cart));
            return "layout/cart/cart";
        }catch (Exception e){
            return "redirect:/logon";
        }
    }

}
