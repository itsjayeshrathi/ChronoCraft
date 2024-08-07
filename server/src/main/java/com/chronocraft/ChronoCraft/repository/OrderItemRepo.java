package com.chronocraft.ChronoCraft.repository;

import com.chronocraft.ChronoCraft.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<Order,Long> {
}
