package com.chronocraft.chronocraft.service.customer;

import com.chronocraft.chronocraft.dto.AddWatchInCartDTO;
import com.chronocraft.chronocraft.dto.CartItemDTO;
import com.chronocraft.chronocraft.dto.OrderDTO;
import com.chronocraft.chronocraft.dto.PlaceOrderDTO;
import com.chronocraft.chronocraft.entity.CartItemEntity;
import com.chronocraft.chronocraft.entity.OrderEntity;
import com.chronocraft.chronocraft.entity.UserEntity;
import com.chronocraft.chronocraft.entity.WatchEntity;
import com.chronocraft.chronocraft.enums.OrderStatus;
import com.chronocraft.chronocraft.repository.CartItemRepository;
import com.chronocraft.chronocraft.repository.OrderRepository;
import com.chronocraft.chronocraft.repository.UserRepository;
import com.chronocraft.chronocraft.repository.WatchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CartServiceImpl implements CartService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final WatchRepository watchRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CartServiceImpl(OrderRepository orderRepository, UserRepository userRepository, CartItemRepository cartItemRepository, WatchRepository watchRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.watchRepository = watchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<?> addProductToCart(AddWatchInCartDTO addWatchInCartDTO) {
        OrderEntity activeOrderEntity = orderRepository.findByUserEntityIdAndOrderStatus(addWatchInCartDTO.getUserId(), OrderStatus.PENDING);
        Optional<CartItemEntity> optionalCartItem = cartItemRepository.findByWatchEntityIdAndOrderEntityIdAndUserEntityId(addWatchInCartDTO.getWatchId(), activeOrderEntity.getId(), addWatchInCartDTO.getUserId());

        if (optionalCartItem.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            Optional<WatchEntity> optionalWatch = watchRepository.findById(addWatchInCartDTO.getWatchId());
            Optional<UserEntity> optionalUser = userRepository.findById(addWatchInCartDTO.getUserId());
            if (optionalWatch.isPresent() && optionalUser.isPresent()) {
                CartItemEntity cart = new CartItemEntity();
                cart.setWatchEntity(optionalWatch.get());
                cart.setPrice(optionalWatch.get().getPrice());
                cart.setOrderEntity(activeOrderEntity);
                cart.setUserEntity(optionalUser.get());
                CartItemEntity updatedCart = cartItemRepository.save(cart);
                activeOrderEntity.setTotalAmount((long) (activeOrderEntity.getTotalAmount() + updatedCart.getPrice()));
                activeOrderEntity.getCartItemEntities().add(cart);
                return ResponseEntity.status(HttpStatus.CREATED).body(cart);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserEntity or WatchEntity not found");
            }

        }
    }

    //    @Override
//    public OrderDTO getCartByUserId(Long userId) {
//        OrderEntity activeOrderEntity = orderRepository.findByUserEntityIdAndOrderStatus(userId, OrderStatus.PENDING);
//        List<CartItemDTO> cartItemDTOList = activeOrderEntity.getCartItemEntities().stream().map(CartItemEntity::getCartDTO).toList();
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setId(activeOrderEntity.getId());
//        orderDTO.setOrderStatus(activeOrderEntity.getOrderStatus());
//        orderDTO.setTotalAmount(activeOrderEntity.getTotalAmount());
//        orderDTO.setCartItems(cartItemDTOList);
//        return orderDTO;
//    }
    @Override
    public OrderDTO getCartByUserId(Long userId) {
        OrderEntity activeOrderEntity = orderRepository.findByUserEntityIdAndOrderStatus(userId, OrderStatus.PENDING);
        List<CartItemDTO> cartItemDTOList = activeOrderEntity.getCartItemEntities().stream().map(cartItemEntity -> modelMapper.map(cartItemEntity, CartItemDTO.class)).toList();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(activeOrderEntity.getId());
        orderDTO.setOrderStatus(activeOrderEntity.getOrderStatus());
        orderDTO.setTotalAmount(activeOrderEntity.getTotalAmount());
        orderDTO.setCartItems(cartItemDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO increaseWatchQuantity(AddWatchInCartDTO addWatchInCartDTO) {
        OrderEntity activeOrderEntity = orderRepository.findByUserEntityIdAndOrderStatus(addWatchInCartDTO.getUserId(), OrderStatus.PENDING);
        Optional<WatchEntity> optionalWatch = watchRepository.findById(addWatchInCartDTO.getWatchId());
        Optional<CartItemEntity> optionalCartItem = cartItemRepository.findByWatchEntityIdAndOrderEntityIdAndUserEntityId(addWatchInCartDTO.getWatchId(), activeOrderEntity.getId(), addWatchInCartDTO.getUserId());
        if (optionalCartItem.isPresent() && optionalWatch.isPresent()) {
            CartItemEntity cartItemEntity = optionalCartItem.get();
            WatchEntity watchEntity = optionalWatch.get();
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + 1);
            cartItemEntity.setPrice(cartItemEntity.getPrice() + optionalWatch.get().getPrice());
            activeOrderEntity.setTotalAmount((long) (activeOrderEntity.getTotalAmount() + watchEntity.getPrice()));
            cartItemRepository.save(cartItemEntity);
            orderRepository.save(activeOrderEntity);
            return modelMapper.map(activeOrderEntity, OrderDTO.class);
        } else {
            return null;
        }

    }

    @Override
    public OrderDTO decreaseWatchQuantity(AddWatchInCartDTO addWatchInCartDTO) {
        OrderEntity activeOrderEntity = orderRepository.findByUserEntityIdAndOrderStatus(addWatchInCartDTO.getUserId(), OrderStatus.PENDING);
        Optional<WatchEntity> optionalWatch = watchRepository.findById(addWatchInCartDTO.getWatchId());
        Optional<CartItemEntity> optionalCartItem = cartItemRepository.findByWatchEntityIdAndOrderEntityIdAndUserEntityId(addWatchInCartDTO.getWatchId(), activeOrderEntity.getId(), addWatchInCartDTO.getUserId());
        if (optionalCartItem.isPresent() && optionalWatch.isPresent()) {
            CartItemEntity cartItemEntity = optionalCartItem.get();
            WatchEntity watchEntity = optionalWatch.get();
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() - 1);
            cartItemEntity.setPrice(cartItemEntity.getPrice() - optionalWatch.get().getPrice());
            activeOrderEntity.setTotalAmount((long) (activeOrderEntity.getTotalAmount() - watchEntity.getPrice()));
            cartItemRepository.save(cartItemEntity);
            orderRepository.save(activeOrderEntity);
            return modelMapper.map(activeOrderEntity, OrderDTO.class);
        } else {
            return null;
        }

    }

    @Override
    public OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO) {
        OrderEntity activeOrderEntity = orderRepository.findByUserEntityIdAndOrderStatus(placeOrderDTO.getUserId(), OrderStatus.PENDING);
        Optional<UserEntity> optionalUser = userRepository.findById(placeOrderDTO.getUserId());
        if (optionalUser.isPresent()) {
            activeOrderEntity.setOrderDescription(placeOrderDTO.getOrderDescription());
            activeOrderEntity.setAddress(placeOrderDTO.getAddress());
            activeOrderEntity.setDate(new Date());
            activeOrderEntity.setOrderStatus(OrderStatus.PLACED);
            activeOrderEntity.setTrackingId(UUID.randomUUID());
            orderRepository.save(activeOrderEntity);
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setTotalAmount(0L);
            orderEntity.setUserEntity(optionalUser.get());
            orderEntity.setOrderStatus(OrderStatus.PENDING);
            orderRepository.save(orderEntity);
            return modelMapper.map(activeOrderEntity, OrderDTO.class);
        }
        return null;
    }

    @Override
    public List<OrderDTO> getMyPlacedOrder(Long userId) {
        return orderRepository.findByUserEntityIdAndOrderStatusIn(userId, List.of(OrderStatus.PLACED, OrderStatus.SHIPPED, OrderStatus.DELIVERED)).stream().map(order -> modelMapper.map(order, OrderDTO.class)).toList();
    }

}