package com.chronocraft.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chronocraft.custom_exceptions.UsernameNotFoundException;
import com.chronocraft.dto.UserDTO;
import com.chronocraft.dto.UserLoginDTO;
import com.chronocraft.entities.User;
@Service
public interface UserService {
    User createUser(UserDTO userDTO);
    UserDTO updateUser(int id, UserDTO userDTO);
    void deleteUser(int id);
    UserDTO getUserById(int id);
    List<UserDTO> getAllUsers();
    User findByEmailIdAndPassword(UserLoginDTO user) throws UsernameNotFoundException;
}
