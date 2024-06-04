package com.shop.controller.web;

import com.shop.model.Cart;
import com.shop.model.CartItem;
import com.shop.model.Product;
import com.shop.service.CartItemService;
import com.shop.service.CartService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartItemService cartItemService;
    @PostMapping("/cart/add-product")
    public ResponseEntity<Map<String, String>> addProductToCart(@RequestBody Map<String, Object> payload
            , HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> responseBody = new HashMap<>();
        try {
            Long productId = Long.valueOf(payload.get("productId").toString());
            if (productId == null || productId <= 0) {
                responseBody.put("message", "Invalid product ID");
                return ResponseEntity.badRequest().body(responseBody);
            }
            CartItem cartItem = new CartItem();
            Product product = new Product();
            product.setId(productId);
            cartItem.setProduct(product);

            cartItemService.create(cartItem);
            responseBody.put("message", "Product added to cart successfully");
            responseBody.put("productId", productId.toString());

            return ResponseEntity.ok(responseBody);

        } catch (Exception e) {
            responseBody.put("message", "An error occurred while adding product to cart");
            return ResponseEntity.status(500).body(responseBody);
        }
    }
}
