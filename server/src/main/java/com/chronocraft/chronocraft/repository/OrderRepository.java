package com.chronocraft.chronocraft.repository;

import com.chronocraft.chronocraft.entity.OrderEntity;
import com.chronocraft.chronocraft.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findByUserEntityIdAndOrderStatus(Long userId, OrderStatus orderStatus);
    List<OrderEntity> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);
    List<OrderEntity>findByUserEntityIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);
}
