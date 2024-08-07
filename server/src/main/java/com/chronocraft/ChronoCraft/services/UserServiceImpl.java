package com.chronocraft.ChronoCraft.services;

import com.chronocraft.ChronoCraft.payloads.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Override
    public UserDTO registerUser() {
        return null;
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
