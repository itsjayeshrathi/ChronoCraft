package com.chronocraft.ChronoCraft.repository;

import com.chronocraft.ChronoCraft.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
