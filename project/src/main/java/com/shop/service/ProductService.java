package com.shop.service;

import com.shop.model.Category;
import com.shop.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<Product> getAll(Integer pageNo);
    Page<Product> searchProduct(String keyword ,Integer pageNo);
    Page<Product> searchProductByCategory(Long id, Integer pageNo);
    List<Product> searchProduct(String keyword);
    List<Product> searchProductByCategory(Long categoryId);

    public List<Product> getAll();
    public Boolean create(Product product);
    public Product find(Long id);
    public Boolean update(Product product);
    public Boolean delete(Long id);
}
