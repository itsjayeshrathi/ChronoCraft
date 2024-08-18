package com.chronocraft.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chronocraft.dto.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(int id, OrderDTO orderDTO);
    void deleteOrder(int id);
    OrderDTO getOrderById(int id);
    List<OrderDTO> getAllOrders();
}
