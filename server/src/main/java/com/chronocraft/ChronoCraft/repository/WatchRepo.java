package com.chronocraft.ChronoCraft.repository;

import com.chronocraft.ChronoCraft.entities.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchRepo extends JpaRepository<Watch,Long> {
}
