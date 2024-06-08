package com.shop.controller.web;

import com.shop.model.CartItem;
import com.shop.model.Product;
import com.shop.service.CartItemService;
import com.shop.service.CartService;
import com.shop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController(value = "productControllerOfWeb")
public class ProductController {
    @Autowired
    ProductService productService;
}
