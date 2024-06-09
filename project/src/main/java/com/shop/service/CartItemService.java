package com.shop.service;

import com.shop.model.Cart;
import com.shop.model.CartItem;

import java.util.List;

public interface CartItemService {
    Boolean create(CartItem cartItem);
    Boolean update(List<CartItem> cartItems);
}
