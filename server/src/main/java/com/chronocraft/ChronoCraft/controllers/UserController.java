package com.chronocraft.ChronoCraft.controllers;

import com.chronocraft.ChronoCraft.payloads.UserDTO;
import com.chronocraft.ChronoCraft.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/user")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO newUserDTO) {
        UserDTO savedUser = userService.registerUser(newUserDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}

