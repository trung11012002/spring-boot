package com.shop.api.web;

import com.shop.model.Email;
import com.shop.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailAPI {
    @Autowired
    private EmailService emailService;
    @PostMapping()
    public ResponseEntity<Void> sendEmail(@RequestBody Email email){
        emailService.sendEmail(email);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
