package com.shop.service.impl;

import com.shop.model.Cart;
import com.shop.model.CartItem;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.repository.CartRepository;
import com.shop.service.CartService;
import com.shop.service.UserService;
import com.shop.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Override
    public Boolean create(Cart cart) {
        try{
            cartRepository.save(cart);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public List<Cart> findByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartNowByUserId() {
        UserDetails userDetails = SecurityUtil.getCurrentUser();
        User user = userService.findByUserName(userDetails.getUsername());
        List<Cart> listCart= cartRepository.findByUserId(user.getId());
        Cart cart =  listCart.get(listCart.size()-1);
        List<CartItem> cartItems = (List<CartItem>) cart.getCartItems();
        Collections.sort(cartItems, Comparator.comparingLong(CartItem::getId).reversed());
        cart.setCartItems(cartItems);
        return cart;
    }

    @Override
    public Double cartTotal(Cart cart) {
        double sum = 0L;
        for(CartItem item : cart.getCartItems()){
            sum+= (item.getQuantity() * item.getProduct().getPrice());
        }
        return sum;
    }


}
