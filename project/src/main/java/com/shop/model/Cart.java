package com.shop.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart extends Base{
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems;
    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "orderId",referencedColumnName = "id")
    private Order order;

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
