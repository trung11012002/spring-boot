package com.shop.service.impl;

import com.shop.model.Email;
import com.shop.model.User;
import com.shop.service.EmailService;
import com.shop.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailSeviceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;
    @Override
    public void sendEmail(Email email) {
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom("vuquangtrung098765@gmail.com");
            helper.setTo(email.getToEmail());
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody());
            mailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean sendVerificationCode(String username, String email) {
        User user = userService.findByUserNameAndEmail(username,email);
        if(user != null){
            // Generate a verification code
            String verificationCode = String.valueOf(new Random().nextInt(999999));
            Email emailModel = new Email();
            emailModel.setToEmail(user.getEmail());
            emailModel.setSubject("Mã code xác minh");
            emailModel.setBody(verificationCode);
            //update code for user db
            userService.updateCode(user,verificationCode);
            //send code
            sendEmail(emailModel);
            return true;
        }
        return false;
    }
}
