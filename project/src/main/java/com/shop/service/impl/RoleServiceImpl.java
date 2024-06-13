package com.shop.service.impl;

import com.shop.model.Role;
import com.shop.repository.RoleRepository;
import com.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role getByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
