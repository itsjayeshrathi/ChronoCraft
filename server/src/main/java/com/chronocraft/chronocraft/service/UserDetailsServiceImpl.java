package com.chronocraft.chronocraft.service;

import com.chronocraft.chronocraft.entity.UserEntity;
import com.chronocraft.chronocraft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findFirstByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("UserEntity not found with email: " + username));

        return new CustomUserDetails(userEntity);
    }
}
