package com.chronocraft.ChronoCraft.services;

import com.chronocraft.ChronoCraft.payloads.UserDTO;

public interface UserService {
    UserDTO registerUser();
    UserDTO getUserById(Long userId);
    UserDTO updateUser(Long userId, UserDTO userDTO);
    String deleteUser(Long userId);
}
