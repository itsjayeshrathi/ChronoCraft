package com.chronocraft.ChronoCraft.entities;

import com.chronocraft.ChronoCraft.enums.Status;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private Double amount;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;
    private Long userId;
}
