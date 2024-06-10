package com.shop.service.impl;

import com.shop.model.Cart;
import com.shop.model.User;
import com.shop.repository.UserRepository;
import com.shop.service.CartService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public User create(User user) {
        try{
            User userOld = findByUserName(user.getUserName());
            if(userOld != null){
                return null;
            }
            user.setEnabled(true);
            user.setPassWord(new BCryptPasswordEncoder().encode(user.getPassWord().trim()));
            return userRepository.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
