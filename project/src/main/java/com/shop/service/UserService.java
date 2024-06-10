package com.shop.service;

import com.shop.model.User;

public interface UserService {
    User findByUserName(String userName);
    User create (User user);
}
