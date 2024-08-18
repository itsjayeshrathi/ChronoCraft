package com.chronocraft.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chronocraft.custom_exceptions.ResourceNotFoundException;
import com.chronocraft.dto.AddToCartDTO;
import com.chronocraft.entities.User;
import com.chronocraft.entities.Watch;
import com.chronocraft.repositories.CartRepository;
import com.chronocraft.repositories.UserRepository;
import com.chronocraft.repositories.WatchRepository;
@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WatchRepository watchRepository;
	
	@Override
	public User findUser(AddToCartDTO addToCartDTO) {
		Optional<User> optionalUser = userRepository.findById(addToCartDTO.getUserId());
		User user = null;
		if(optionalUser.isPresent()) {
			user = optionalUser.get();
		}else {
			throw new ResourceNotFoundException("user not found with id:"+addToCartDTO.getUserId());
		}
		return user;
	}
	@Override
	public Watch findWatch(AddToCartDTO addToCartDTO) {
		Optional<Watch> optionalProduct = watchRepository.findById(addToCartDTO.getWatchId());
		Watch watch= null;
		if(optionalProduct.isPresent()) {
			watch = optionalProduct.get();
		}else {
			throw new ResourceNotFoundException("Watch not found with id:"+addToCartDTO.getWatchId());
		}
		return watch;
	}
	
}
