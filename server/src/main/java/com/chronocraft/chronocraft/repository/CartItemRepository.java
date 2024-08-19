package com.chronocraft.chronocraft.repository;

import com.chronocraft.chronocraft.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    Optional<CartItemEntity> findByWatchEntityIdAndOrderEntityIdAndUserEntityId(Long watchId, Long id, Long userId);
}
