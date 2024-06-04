package com.shop.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "role")
public class Role extends Base{
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "role")
    private Set<UserRole> roleUsers;

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(Set<UserRole> roleUsers) {
        this.roleUsers = roleUsers;
    }
}
