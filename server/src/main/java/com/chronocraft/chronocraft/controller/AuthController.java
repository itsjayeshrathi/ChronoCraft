
package com.chronocraft.chronocraft.controller;

import com.chronocraft.chronocraft.dto.AuthenticationRequest;
import com.chronocraft.chronocraft.dto.SignupRequest;
import com.chronocraft.chronocraft.dto.UserDTO;
import com.chronocraft.chronocraft.entity.UserEntity;
import com.chronocraft.chronocraft.enums.UserRole;
import com.chronocraft.chronocraft.repository.UserRepository;
import com.chronocraft.chronocraft.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
public class AuthController {
    public final AuthenticationManager authenticationManager;
    private final AuthService   authService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AuthService authService,UserRepository userRepository, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        Optional<UserEntity> userDTO = userRepository.findFirstByEmail(authenticationRequest.getUsername());
        UserRole role = userDTO.get().getRole();
        return new ResponseEntity<>(role,HttpStatus.OK);

    }
    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignupRequest signupRequest) {
        if(authService.hasUserWithEmail(signupRequest.getEmail())) {
            return new ResponseEntity<>("UserEntity with email already exists", HttpStatus.BAD_REQUEST);
        }
        UserDTO userDTO = authService.createUser(signupRequest);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}
