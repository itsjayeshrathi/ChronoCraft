package com.chronocraft.chronocraft.service.customer.cart;

import com.chronocraft.chronocraft.dto.AddWatchInCartDTO;
import com.chronocraft.chronocraft.dto.OrderDTO;
import com.chronocraft.chronocraft.dto.PlaceOrderDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddWatchInCartDTO addWatchInCartDTO);

    OrderDTO getCartByUserId(Long userId);

    OrderDTO increaseWatchQuantity(AddWatchInCartDTO addWatchInCartDTO);
    OrderDTO decreaseWatchQuantity(AddWatchInCartDTO addWatchInCartDTO);
    OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO);
    List<OrderDTO> getMyPlacedOrder(Long userId);
}
