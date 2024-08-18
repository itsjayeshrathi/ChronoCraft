package com.chronocraft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chronocraft.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
