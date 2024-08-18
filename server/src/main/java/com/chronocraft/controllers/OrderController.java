package com.chronocraft.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chronocraft.dto.OrderDTO;
import com.chronocraft.dto.OrderDataResponseDTO;
import com.chronocraft.entities.Cart;
import com.chronocraft.entities.Order;
import com.chronocraft.entities.Watch;
import com.chronocraft.repositories.CartRepository;
import com.chronocraft.repositories.OrderRepository;
import com.chronocraft.repositories.WatchRepository;
import com.chronocraft.services.OrderService;
import com.chronocraft.utility.IdGeneration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepoditory;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private WatchRepository watchRepository;
    
    ObjectMapper objectMapper= new ObjectMapper();

    @PostMapping("order")
	public ResponseEntity createCustomerOrder(@RequestParam("userId") int userId) throws JsonProcessingException {

		System.out.println("request came for ORDER FOR CUSTOMER ID : " + userId);

		String orderId = IdGeneration.getAlphaNumericOrderId();

		List<Cart> userCarts = cartRepository.findCartsByUserId(userId);
		
		int FinalProductQuantity;

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String formatDateTime = currentDateTime.format(formatter);

		for (Cart cart : userCarts) {

			Order order = new Order();
			order.setOrderId(orderId);
			order.setUser(cart.getUser());
			order.setWatch(cart.getWatch());
			order.setQuantity(cart.getQuantity());
			order.setOrderDate(formatDateTime);

			orderRepoditory.save(order);
			cartRepository.delete(cart);
			
			Optional<Watch> optionalProduct = watchRepository.findById(cart.getWatch().getId());
			Watch watch = null;
			if(optionalProduct.isPresent()) {
				watch = optionalProduct.get();
			}
			 FinalProductQuantity= watch.getQuantity()-order.getQuantity();
			 watch.setQuantity(FinalProductQuantity);
			 watchRepository.save(watch);
		}

		System.out.println("response sent!!!");

		return new ResponseEntity("ORDER SUCCESS", HttpStatus.OK);

	}

    @GetMapping("myorder")
	public ResponseEntity getMyOrder(@RequestParam("userId") int userId) throws JsonProcessingException {

		System.out.println("request came for MY ORDER for USER ID : " + userId);

		List<Order> userOrder = orderRepoditory.findByUserId(userId);

		OrderDataResponseDTO orderResponse = new OrderDataResponseDTO();

		List<OrderDTO> orderDatas = new ArrayList<>();

		for (Order order : userOrder) {
			OrderDTO orderData = new OrderDTO();
			orderData.setOrderId(order.getOrderId());
			orderData.setWatchDescription(order.getWatch().getDescription_name());
			orderData.setWatchName(order.getWatch().getBrand());
			orderData.setWatchImage(order.getWatch().getImagePath());
			orderData.setQuantity(order.getQuantity());
			orderData.setOrderDate(order.getOrderDate());
			orderData.setWatchId(order.getWatch().getId());
			orderData.setTotalPrice(
					String.valueOf(order.getQuantity() * order.getWatch().getPrice()));
			orderData.setUserName(order.getUser().getFirstName()+" "+order.getUser().getLastName());
			orderData.setUserPhone(order.getUser().getPhoneNo());
			orderData.setAddress(order.getUser().getAddress());
			orderData.setUserId(order.getUser().getId());
			orderDatas.add(orderData);
		}

		String json = objectMapper.writeValueAsString(orderDatas);

		System.out.println(json);

		return new ResponseEntity(orderDatas, HttpStatus.OK);

	}
    
    @GetMapping("admin/allorder")
	public ResponseEntity getAllOrder() throws JsonProcessingException {

		System.out.println("request came for FETCH ALL ORDERS");

		List<Order> userOrder = orderRepoditory.findAll();

		OrderDataResponseDTO orderResponse = new OrderDataResponseDTO();

		List<OrderDTO> orderDatas = new ArrayList<>();

		for (Order order : userOrder) {
			OrderDTO orderData = new OrderDTO();
			orderData.setOrderId(order.getOrderId());
			orderData.setWatchDescription(order.getWatch().getDescription_name());
			orderData.setWatchName(order.getWatch().getBrand());
			//orderData.setWatchImage(order.getWatch().getImageName());
			orderData.setQuantity(order.getQuantity());
			orderData.setOrderDate(order.getOrderDate());
			orderData.setWatchId(order.getWatch().getId());
			orderData.setTotalPrice(
					String.valueOf(order.getQuantity() * order.getWatch().getPrice()));
			orderData.setUserId(order.getUser().getId());
			orderData.setUserName(order.getUser().getFirstName() + " " + order.getUser().getLastName());
			orderData.setUserPhone(order.getUser().getPhoneNo());
			orderData.setAddress(order.getUser().getAddress());
			
			orderDatas.add(orderData);

		}

		String json = objectMapper.writeValueAsString(orderDatas);

		System.out.println(json);

		System.out.println("response sent !!!");

		return new ResponseEntity(orderDatas, HttpStatus.OK);

	}
    
    
    @GetMapping("admin/showorder")
	public ResponseEntity getOrdersByOrderId(@RequestParam("orderId") String orderId) throws JsonProcessingException {

		System.out.println("request came for FETCH ORDERS BY ORDER ID : " + orderId);

		List<Order> userOrder = orderRepoditory.findByOrderId(orderId);

		List<OrderDTO> orderDatas = new ArrayList<>();

		for (Order order : userOrder) {
			OrderDTO orderData = new OrderDTO();
			orderData.setOrderId(order.getOrderId());
			orderData.setWatchDescription(order.getWatch().getDescription_name());
			orderData.setWatchName(order.getWatch().getBrand());
			//orderData.setWatchImage(order.getWatch().getImageName());
			orderData.setQuantity(order.getQuantity());
			orderData.setOrderDate(order.getOrderDate());
			orderData.setWatchId(order.getWatch().getId());
			orderData.setTotalPrice(
					String.valueOf(order.getQuantity() * order.getWatch().getPrice()));
			orderData.setUserId(order.getUser().getId());
			orderData.setUserName(order.getUser().getFirstName() + " " + order.getUser().getLastName());
			orderData.setUserPhone(order.getUser().getPhoneNo());
			orderData.setAddress(order.getUser().getAddress());
			
			orderDatas.add(orderData);

		}

		String json = objectMapper.writeValueAsString(orderDatas);

		System.out.println(json);

		System.out.println("response sent !!!");

		return new ResponseEntity(orderDatas, HttpStatus.OK);

	}
}
