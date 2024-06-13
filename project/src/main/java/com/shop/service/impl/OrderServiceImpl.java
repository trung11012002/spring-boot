package com.shop.service.impl;

import com.shop.model.Cart;
import com.shop.model.Order;
import com.shop.model.User;
import com.shop.repository.OrderRepository;
import com.shop.service.CartService;
import com.shop.service.OrderService;
import com.shop.service.UserService;
import com.shop.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Override
    @Transactional
    public Boolean create(Order order) {
        try{
            UserDetails userDetails = SecurityUtil.getCurrentUser();
            User user = userService.findByUserName(userDetails.getUsername());
            Cart cart = cartService.getCartNowByUserId();
            order.setCart(cart);
            order.setUser(user);
            if(cart.getUser().getId() != user.getId()){
                return false;
            }
            orderRepository.save(order);
            //tao gio hang moi
            Cart cartNew = new Cart();
            cartNew.setUser(user);
            cartService.create(cartNew);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Order> getByUserId() {
        UserDetails userDetails = SecurityUtil.getCurrentUser();
        User user = userService.findByUserName(userDetails.getUsername());
        return orderRepository.findByUserId(user.getId());
    }
}
