package com.chronocraft.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chronocraft.dto.AddToCartDTO;
import com.chronocraft.dto.CartDataResponse;
import com.chronocraft.dto.CartResponse;
import com.chronocraft.entities.Cart;
import com.chronocraft.entities.User;
import com.chronocraft.entities.Watch;
import com.chronocraft.repositories.CartRepository;
import com.chronocraft.services.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/users/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartRepository cartRepository;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody AddToCartDTO addToCartDTO){
		System.out.println("request came for ADD watch TO CART");
		User user= cartService.findUser(addToCartDTO);
		Watch watch = cartService.findWatch(addToCartDTO);
		
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setWatch(watch);
		cart.setQuantity(addToCartDTO.getQuantity());
		cartRepository.save(cart);
		System.out.println("watch is added to cart..");
		return new ResponseEntity<Object>(HttpStatus.OK);
	} 

	@GetMapping("/mycart")
	public ResponseEntity<CartResponse> getMyCart(@RequestParam("userId") int userId) throws JsonProcessingException {
		
		System.out.println("request came for MY CART for USER ID : "+userId);
		
		List<CartDataResponse> cartDatasList = new ArrayList<>();
		
		List<Cart> userCarts = cartRepository.findCartsByUserId(userId);
		
		double totalCartPrice = 0;
		
		for (Cart cart : userCarts) {
			CartDataResponse cartData = new CartDataResponse();
			cartData.setCartId(cart.getId());
			cartData.setWatchDescription(cart.getWatch().getDescription_name());
			cartData.setWatchName(cart.getWatch().getBrand());
			cartData.setWatchImage(cart.getWatch().getImagePath());
			cartData.setQuantity(cart.getQuantity());
			cartData.setWatchId(cart.getWatch().getId());
			
			cartDatasList.add(cartData);
			 			
			double productPrice = cart.getWatch().getPrice();
			
			totalCartPrice =  totalCartPrice + (cart.getQuantity() * productPrice);
			
		}
		
		CartResponse cartResponse = new CartResponse();
		cartResponse.setTotalCartPrice(String.valueOf(totalCartPrice));
		cartResponse.setCartData(cartDatasList);
		
		String json = objectMapper.writeValueAsString(cartResponse);
		
		System.out.println(json);
		
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
		
	}
	
	@DeleteMapping("mycart/remove")
	public ResponseEntity<String> removeCartItem(@RequestParam("cartId") int cartId) throws JsonProcessingException {
		
		System.out.println("request came for DELETE CART ITEM WHOSE ID IS : "+cartId);
		
		Optional<Cart> optionalCart = cartRepository.findById(cartId);
		Cart cart = new Cart();
		
		if(optionalCart.isPresent()) {
			cart = optionalCart.get();
		}
		
		this.cartRepository.delete(cart);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		
	}
}
