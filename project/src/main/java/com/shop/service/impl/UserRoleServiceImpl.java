package com.shop.service.impl;

import com.shop.model.UserRole;
import com.shop.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements com.shop.service.UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public Boolean create(UserRole userRole) {
        try{
            userRoleRepository.save(userRole);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
