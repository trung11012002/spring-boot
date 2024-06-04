package com.shop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users_roles")
public class UserRole extends Base{

    @ManyToOne
    @JoinColumn(name = "userId" , referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "roleId" , referencedColumnName = "id")
    private Role role;
    public UserRole() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
