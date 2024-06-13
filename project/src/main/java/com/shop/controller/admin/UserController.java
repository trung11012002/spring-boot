package com.shop.controller.admin;

import com.shop.model.Cart;
import com.shop.model.User;
import com.shop.service.CartService;
import com.shop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "userControllerOfAdmin")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @RequestMapping("/logon")
    public String logon(){
        return("admin/logon");
    }
    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user" , user);
        return "admin/register";
    }
    @PostMapping("/register")
    public String registerUser(Model model, @ModelAttribute("user") User user){
        user = userService.create(user);
        if(user != null){
            //Create new cart
            Cart cartNew = new Cart();
            cartNew.setUser(user);
            cartService.create(cartNew);
            model.addAttribute("message" , "Đăng ký thành công");
            model.addAttribute("messageType", "success");
        }else{
            model.addAttribute("message", "Đăng ký thất bại");
            model.addAttribute("messageType", "error");
        }
        return "admin/register";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/logon";
    }
}
