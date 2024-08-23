package com.chronocraft.chronocraft.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private Integer quantity;
    private Double price;
    private Long watchId;
    private Long orderId;
    private String watchName;
    private String returnedImage;
    private Long userId;
}
