package com.shop.api.web;

import com.shop.model.Product;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "productAPIOfWeb")
public class ProductAPI {
    @Autowired
    ProductService productService;
    @GetMapping("/product/detail")
    public ResponseEntity<Product> productDetail(@Param("productId") Long productId) {
        try {
            if (productId == null || productId <= 0) {
                return ResponseEntity.badRequest().build();
            }
            Product product = productService.find(productId);
            if (product == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
