package com.chronocraft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronocraft.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	List<Cart> findCartsByUserId(int userId);
}
