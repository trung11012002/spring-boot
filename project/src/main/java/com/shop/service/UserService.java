package com.shop.service;

import com.shop.model.User;

public interface UserService {
    User findByUserName(String userName);
    User create (User user);
    User getUserNow();
    Boolean changePassword(String newPassword);
    Boolean update(User user);
    User findByUserNameAndEmail(String username, String email);
    Boolean updateCode(User user , String code);
}
