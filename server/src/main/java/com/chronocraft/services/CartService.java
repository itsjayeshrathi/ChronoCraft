package com.chronocraft.services;

import org.springframework.stereotype.Service;

import com.chronocraft.dto.AddToCartDTO;
import com.chronocraft.entities.User;
import com.chronocraft.entities.Watch;

@Service
public interface CartService {
	
	public User findUser(AddToCartDTO addToCartDTO);
	public Watch findWatch(AddToCartDTO addToCartDTO);
}
