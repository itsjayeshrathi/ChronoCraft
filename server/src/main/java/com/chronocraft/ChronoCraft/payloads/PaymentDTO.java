package com.chronocraft.ChronoCraft.payloads;
import java.time.LocalDateTime;

public class PaymentDTO {
    private Long id;
    private Double amount;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
