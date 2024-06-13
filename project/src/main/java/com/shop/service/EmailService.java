package com.shop.service;

import com.shop.model.Email;

public interface EmailService {
    void sendEmail(Email email);
    boolean sendVerificationCode(String username, String email);
}
