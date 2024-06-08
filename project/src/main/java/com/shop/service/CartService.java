package com.shop.service;


import com.shop.model.Cart;
import com.shop.model.Category;

import java.util.List;

public interface CartService {
    Boolean create(Cart cart);
    Cart findById(Long id);
    List<Cart> findByUserId(Long userId);
    List<Cart> getAll();
    Cart getCartNowByUserId();
}
