package com.chronocraft.chronocraft.controller.admin;

import com.chronocraft.chronocraft.dto.OrderDTO;
import com.chronocraft.chronocraft.enums.OrderStatus;
import com.chronocraft.chronocraft.service.admin.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    private final OrderService orderService;
    @Autowired
    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/placedOrders")
    public ResponseEntity<List<OrderDTO>>getAllPlacedOrder(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/order/{orderId}/{orderStatus}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable OrderStatus orderStatus){
       OrderDTO orderDTO = orderService.changeOrderStatus(orderId, orderStatus);
       if(orderDTO != null){
           return ResponseEntity.ok(orderDTO);
       }
       return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

}
