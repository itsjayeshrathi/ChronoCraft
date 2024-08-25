package com.chronocraft.chronocraft.controller;

import com.chronocraft.chronocraft.dto.AuthenticationRequest;
import com.chronocraft.chronocraft.dto.AuthenticationResponse;
import com.chronocraft.chronocraft.dto.SignupRequest;
import com.chronocraft.chronocraft.dto.UserDTO;
import com.chronocraft.chronocraft.jwt.JwtTokenUtil;
import com.chronocraft.chronocraft.service.auth.AuthService;
import com.chronocraft.chronocraft.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(token, userDetails.getUserEntity().getRole()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignupRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return new ResponseEntity<>("User with email already exists", HttpStatus.BAD_REQUEST);
        }
        UserDTO userDTO = authService.createUser(signupRequest);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}