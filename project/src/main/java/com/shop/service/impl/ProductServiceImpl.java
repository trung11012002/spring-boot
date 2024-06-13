package com.shop.service.impl;

import com.shop.model.Category;
import com.shop.model.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.ProductService;
import com.shop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Page<Product> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,2);
        return productRepository.findAll(pageable);
    }
    @Override
    public Page<Product> searchProduct(String keyword, Integer pageNo) {
        List<Product> list  = searchProduct(keyword);
        Pageable pageable = PageRequest.of(pageNo-1,2);
        Integer start = (int)pageable.getOffset();
        Integer end =(int)((pageable.getOffset()+pageable.getPageSize()) > list.size() ? list.size() : (pageable.getOffset() + pageable.getPageSize()));
        list = list.subList(start,end);
        return new PageImpl<Product>(list,pageable,this.searchProduct(keyword).size());
    }

    @Override
    public Page<Product> searchProductByCategory(Long categoryId, Integer pageNo) {
        List<Product> list  = searchProductByCategory(categoryId);
        Pageable pageable = PageRequest.of(pageNo-1,2);
        Integer start = (int)pageable.getOffset();
        Integer end =(int)((pageable.getOffset()+pageable.getPageSize()) > list.size() ? list.size() : (pageable.getOffset() + pageable.getPageSize()));
        list = list.subList(start,end);
        return new PageImpl<Product>(list,pageable,searchProductByCategory(categoryId).size());
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword);
    }

    @Override
    public List<Product> searchProductByCategory(Long categoryId) {
        return productRepository.searchByCategoryId(categoryId);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
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
    public Product find(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
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
    @Transactional
    public Boolean delete(Long id) {
        try {
            this.productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
