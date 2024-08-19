package com.chronocraft.chronocraft.controller.customer;

import com.chronocraft.chronocraft.dto.AddWatchInCartDTO;
import com.chronocraft.chronocraft.dto.OrderDTO;
import com.chronocraft.chronocraft.dto.PlaceOrderDTO;
import com.chronocraft.chronocraft.service.customer.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerCartController {
    private final CartService cartService;

    @Autowired
    public CustomerCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public ResponseEntity<?> addWatchToCart(@RequestBody AddWatchInCartDTO addWatchInCartDTO) {
        return cartService.addProductToCart(addWatchInCartDTO);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
        OrderDTO orderDTO = cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }

    @PostMapping("/addition")
    public ResponseEntity<OrderDTO> increaseWatchQuantity(@RequestBody AddWatchInCartDTO addWatchInCartDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.increaseWatchQuantity(addWatchInCartDTO));
    }

    @PostMapping("/deduction")
    public ResponseEntity<OrderDTO> decreaseWatchQuantity(@RequestBody AddWatchInCartDTO addWatchInCartDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.decreaseWatchQuantity(addWatchInCartDTO));
    }
    @PostMapping("/placeOrder")
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.placeOrder(placeOrderDTO));
    }
    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<List<OrderDTO>> getMyPlacedOrder(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getMyPlacedOrder(userId));
    }
}
