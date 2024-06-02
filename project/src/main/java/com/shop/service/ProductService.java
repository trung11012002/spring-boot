package com.shop.service;

import com.shop.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAll();
    public Boolean create(Product product);
    public Product find(Integer id);
    public Boolean update(Product product);
    public Boolean delete(Integer id);
}
