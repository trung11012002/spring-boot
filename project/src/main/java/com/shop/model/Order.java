package com.shop.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends Base{
    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "status_order")
    private Boolean statusOrder;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> carts;

}
