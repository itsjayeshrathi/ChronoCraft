package com.chronocraft.ChronoCraft.payloads;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Long id;
    private UserDTO user;
    private List<OrderItemDTO> items;
    private PaymentDTO payment;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
