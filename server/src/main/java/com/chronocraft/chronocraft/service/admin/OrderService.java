package com.chronocraft.chronocraft.service.admin;

import com.chronocraft.chronocraft.dto.OrderDTO;
import com.chronocraft.chronocraft.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO changeOrderStatus(Long orderId, OrderStatus orderStatus);

}
