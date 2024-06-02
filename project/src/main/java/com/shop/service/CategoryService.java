package com.shop.service;

import com.shop.model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAll();
    public Boolean create(Category category);
    public Category findById(Integer id);
    public Boolean update(Category category);
    public Boolean delete(Integer id);
}
