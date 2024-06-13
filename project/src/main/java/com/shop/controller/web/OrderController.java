package com.shop.controller.web;

import com.shop.model.Cart;
import com.shop.model.Order;
import com.shop.service.CartService;
import com.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @GetMapping("/checkout")
    public String checkout(Model model) {
        Cart cart = cartService.getCartNowByUserId();
        Order order = new Order();
        model.addAttribute("order" ,order);
        model.addAttribute("cart",cart);
        model.addAttribute("cartTotal" , cartService.cartTotal(cart));
        return "layout/checkout/checkout";
    }
    @PostMapping("/order")
    public String order(Model model, @ModelAttribute("order") Order order) {
        Boolean check  = orderService.create(order);
        if(check){
            return "redirect:/";
        }else{
            return "redirect:/logon";
        }
    }
    @GetMapping("/list-order")
    public String listOder(Model model){
        model.addAttribute("listOrder" , orderService.getByUserId());
        return  "layout/order/order";
    }
}
