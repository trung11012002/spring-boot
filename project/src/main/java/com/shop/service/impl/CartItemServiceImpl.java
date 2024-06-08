package com.shop.service.impl;

import com.shop.model.Cart;
import com.shop.model.CartItem;
import com.shop.model.User;
import com.shop.repository.CartItemRepository;
import com.shop.service.CartItemService;
import com.shop.service.CartService;
import com.shop.service.UserService;
import com.shop.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @Override
    public Boolean create(CartItem cartItem) {
        UserDetails userDetails = SecurityUtil.getCurrentUser();
        User user = userService.findByUserName(userDetails.getUsername());
        List<Cart> listCart = cartService.findByUserId(user.getId());
        Cart cart = listCart.get(listCart.size()-1);
        cartItem.setCart(cart);
        //kiem tra xem trong cart da co sp nay chua
        int check = 0;
        for(CartItem item : cart.getCartItems()){
            if(item.getProduct().getId() == cartItem.getProduct().getId()){
                cartItem = item;
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                check = 1;
                break;
            }
        }
        if(check == 0){
            cartItem.setQuantity(1);
        }

        try{
            cartItemRepository.save(cartItem);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
