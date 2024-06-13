package com.shop.controller.web;

import com.shop.model.Email;
import com.shop.model.User;
import com.shop.service.EmailService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller(value = "userControllerOfWeb")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @GetMapping("/user-information")
    public String userInformation(Model model){
        model.addAttribute("user" ,userService.getUserNow());
        return "layout/user/information";
    }
    @GetMapping("/change-password")
    public String changePassword(Model model){
        return "layout/user/change-password";
    }
    @PostMapping("/change-password")
    public String changePassword(Model model , @Param("newPassword") String newPassword){
        if(userService.changePassword(newPassword)){
            model.addAttribute("message" , "Thay đổi thành công");
            model.addAttribute("messageType", "success");
        }else{
            model.addAttribute("message", "Thay đổi thất bại");
            model.addAttribute("messageType", "error");
        }
        return "layout/user/change-password";
    }
    @PostMapping("/user-update")
    public String userUpdate(Model model , @ModelAttribute("user") User user){
        if(userService.update(user)){
            model.addAttribute("message" , "Thay đổi thành công");
            model.addAttribute("messageType", "success");
        }else{
            model.addAttribute("message", "Thay đổi thất bại");
            model.addAttribute("messageType", "error");
        }
        return "layout/user/information";
    }
    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        return "layout/user/forgot-password";
    }
    @PostMapping("/send-verification-code")
    public String sendCode(Model model ,@Param("username") String username ,@Param("email") String email){
        if(emailService.sendVerificationCode(username,email)){
            
        }else{
            model.addAttribute("message", "Thông tin tài khoản hoặc gmail không đúng");
            model.addAttribute("messageType", "error");
            return "layout/user/forgot-password";
        }
        return "";
    }
}
