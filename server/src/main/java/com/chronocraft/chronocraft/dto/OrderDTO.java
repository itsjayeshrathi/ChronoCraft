package com.chronocraft.chronocraft.dto;

import com.chronocraft.chronocraft.enums.OrderStatus;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {

    private Long id;
    private String orderDescription;
    private LocalDateTime date;
    private String payment;
    private OrderStatus orderStatus;
    private Long totalAmount;
    private UUID trackingId;
    private String userName;
    private List<CartItemDTO> cartItems;
}
