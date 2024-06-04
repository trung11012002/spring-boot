package com.shop.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cart_item")
public class CartItem extends Base{
    @Column(name =  "quantity")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "cartId",referencedColumnName = "id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "productId",referencedColumnName = "id")
    private Product product;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
