package com.shop.service.impl;

import com.shop.model.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.ProductService;
import com.shop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Boolean create(Product product) {
        try {
            this.productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product find(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Boolean update(Product product) {
        Product oldProduct = productRepository.findById(product.getId()).get();
        if (product.getImage() == null) {
            product.setImage(oldProduct.getImage());
        }
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            this.productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
