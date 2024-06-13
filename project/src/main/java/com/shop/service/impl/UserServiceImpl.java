package com.shop.service.impl;

import com.shop.model.Cart;
import com.shop.model.Role;
import com.shop.model.User;
import com.shop.model.UserRole;
import com.shop.repository.UserRepository;
import com.shop.service.CartService;
import com.shop.service.RoleService;
import com.shop.service.UserRoleService;
import com.shop.service.UserService;
import com.shop.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
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
            user = userRepository.save(user);
            //tao role cho nguoi dung
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            Role role = roleService.getByName("USER");
            userRole.setRole(role);
            userRoleService.create(userRole);
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserNow() {
        UserDetails userDetails = SecurityUtil.getCurrentUser();
        return findByUserName(userDetails.getUsername());
    }

    @Override
    @Transactional
    public Boolean changePassword(String newPassword) {
        UserDetails userDetails = SecurityUtil.getCurrentUser();
        User user = findByUserName(userDetails.getUsername());
        try{
            user.setPassWord(new BCryptPasswordEncoder().encode(newPassword.trim()));
            userRepository.save(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean update(User user) {
        try{
            User userOld = userRepository.findById(user.getId()).get();
            userOld.setFullName(user.getFullName());
            userOld.setEmail(user.getEmail());
            userOld.setTelephone(user.getTelephone());
            userOld.setAddress(user.getAddress());
            userRepository.save(userOld);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findByUserNameAndEmail(String username, String email) {
        return userRepository.findByUserNameAndEmail(username,email);
    }

    @Override
    @Transactional
    public Boolean updateCode(User user, String code) {
        user.setCode(code);
        try{
            userRepository.save(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
