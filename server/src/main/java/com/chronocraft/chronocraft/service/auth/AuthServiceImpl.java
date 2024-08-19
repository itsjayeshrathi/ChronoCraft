package com.chronocraft.chronocraft.service.auth;

import com.chronocraft.chronocraft.dto.SignupRequest;
import com.chronocraft.chronocraft.dto.UserDTO;
import com.chronocraft.chronocraft.entity.OrderEntity;
import com.chronocraft.chronocraft.entity.UserEntity;
import com.chronocraft.chronocraft.enums.OrderStatus;
import com.chronocraft.chronocraft.repository.OrderRepository;
import com.chronocraft.chronocraft.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.chronocraft.chronocraft.enums.UserRole;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, OrderRepository orderRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO createUser(SignupRequest signupRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signupRequest.getEmail());
        userEntity.setName(signupRequest.getName());
        userEntity.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userEntity.setRole(UserRole.CUSTOMER);
        UserEntity createUserEntity = userRepository.save(userEntity);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setTotalAmount(0L);
        orderEntity.setUserEntity(createUserEntity);
        orderEntity.setOrderStatus(OrderStatus.PENDING);
        orderRepository.save(orderEntity);

        return modelMapper.map(createUserEntity, UserDTO.class);
    }


    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    @Override
    public void createAdminAccount() {
        UserEntity adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if (null == adminAccount) {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("admin@test.com");
            userEntity.setName("Admin");
            userEntity.setRole(UserRole.ADMIN);
            userEntity.setPassword(passwordEncoder.encode("admin@567"));
            userRepository.save(userEntity);
        }
    }
}
