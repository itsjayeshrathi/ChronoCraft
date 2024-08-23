package com.chronocraft.chronocraft.repository;
import com.chronocraft.chronocraft.entity.WatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchRepository extends JpaRepository<WatchEntity, Long> {
    List<WatchEntity> findAllByNameContaining(String name);
}
