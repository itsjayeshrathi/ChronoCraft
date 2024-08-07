package com.chronocraft.ChronoCraft.services;

import com.chronocraft.ChronoCraft.config.ModelMapperConfig;
import com.chronocraft.ChronoCraft.entities.User;
import com.chronocraft.ChronoCraft.enums.UserRole;
import com.chronocraft.ChronoCraft.payloads.UserDTO;
import com.chronocraft.ChronoCraft.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserServiceImpl implements UserService{


    private final UserRepo userRepo;
    private final ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper){
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }
    @Override
    public UserDTO registerUser(UserDTO user) {
        User tempUser  = modelMapper.map(user, User.class);
        tempUser.setRole(UserRole.CUSTOMER);
        tempUser.setCreatedAt(LocalDateTime.now());
        tempUser.setUpdatedAt(LocalDateTime.now());
        tempUser.setOrders(null);
        userRepo.save(tempUser);
        return user;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return null;
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        return null;
    }

    @Override
    public String deleteUser(Long userId) {
        return "";
    }
}
