package com.shop.service;

import com.shop.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    public List<Category> getAll();
    public Boolean create(Category category);
    public Category findById(Long id);
    public Boolean update(Category category);
    public Boolean delete(Long id);
    List<Category> searchCategory(String keyword);
    Page<Category> getAll(Integer pageNo);
    Page<Category> searchCategory(String keyword ,Integer pageNo);
}
