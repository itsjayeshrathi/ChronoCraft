package com.chronocraft.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chronocraft.custom_exceptions.ResourceNotFoundException;
import com.chronocraft.dto.OrderDTO;
import com.chronocraft.entities.Order;
import com.chronocraft.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        // Set user and order items here
        orderRepository.save(order);
        //orderDTO.setId(order.getId());
        return orderDTO;
    }

    @Override
    public OrderDTO updateOrder(int id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        // Update user and order items here
        orderRepository.save(order);
        return orderDTO;
    }

    @Override
    public void deleteOrder(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.delete(order);
    }

    @Override
    public OrderDTO getOrderById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        OrderDTO orderDTO = new OrderDTO();
       // orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        // Set order items here
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(order -> {
            OrderDTO orderDTO = new OrderDTO();
           // orderDTO.setId(order.getId());
            orderDTO.setUserId(order.getUser().getId());
            // Set order items here
            return orderDTO;
        }).collect(Collectors.toList());
    }
}
