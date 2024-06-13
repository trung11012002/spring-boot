package com.shop.repository;

import com.shop.model.Category;
import com.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p from Product p where p.productName LIKE %?1%")
    List<Product> searchProduct(String keyword);
    List<Product> searchByCategoryId(Long categoryId);
}
