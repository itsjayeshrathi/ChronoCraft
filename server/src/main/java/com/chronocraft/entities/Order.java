package com.chronocraft.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String orderId;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Watch watch;

    private int quantity;
	
	private String orderDate;  // 13-01-2022 10:00 PM
	
	private String deliveryStatus;
//    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
//    private Payment payment;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<OrderItem> orderItems;

    // Getters and Setters
}
