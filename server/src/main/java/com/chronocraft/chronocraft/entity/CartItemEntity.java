package com.chronocraft.chronocraft.entity;

import com.chronocraft.chronocraft.dto.CartItemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "watch_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private WatchEntity watchEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public CartItemDTO getCartDTO() {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(this.id);
        cartItemDTO.setPrice(this.price);
        cartItemDTO.setWatchId(this.watchEntity.getId());
        cartItemDTO.setQuantity(this.quantity);
        cartItemDTO.setUserId(this.userEntity.getId());
        cartItemDTO.setWatchName(this.watchEntity.getName());
        cartItemDTO.setReturnedImage(this.watchEntity.getImageUrl());
        return cartItemDTO;
    }
}
