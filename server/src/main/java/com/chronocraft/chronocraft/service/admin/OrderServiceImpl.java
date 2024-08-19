package com.chronocraft.chronocraft.service.admin;

import com.chronocraft.chronocraft.dto.OrderDTO;
import com.chronocraft.chronocraft.entity.OrderEntity;
import com.chronocraft.chronocraft.enums.OrderStatus;
import com.chronocraft.chronocraft.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<OrderDTO> getAllOrders() {
       List<OrderEntity> orderEntityList = orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.PLACED,OrderStatus.SHIPPED,OrderStatus.DELIVERED));
         return orderEntityList.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }
    @Override
    public OrderDTO changeOrderStatus(Long orderId, OrderStatus orderStatus){
        Optional<OrderEntity> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            OrderEntity orderEntity = optionalOrder.get();
            orderEntity.setOrderStatus(orderStatus);
            orderRepository.save(orderEntity);
            return modelMapper.map(orderEntity, OrderDTO.class);
        }
        return null;
    }
}
