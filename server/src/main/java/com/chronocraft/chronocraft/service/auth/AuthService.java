package com.chronocraft.chronocraft.service.auth;

import com.chronocraft.chronocraft.dto.SignupRequest;
import com.chronocraft.chronocraft.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupRequest signupRequest);

    boolean hasUserWithEmail(String email);

    void createAdminAccount();
}
