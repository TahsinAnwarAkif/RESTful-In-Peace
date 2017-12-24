package com.management.hospital.sevice;

import com.management.hospital.controller.Constants;
import com.management.hospital.model.Role;
import com.management.hospital.model.User;
import com.management.hospital.repository.RoleRepository;
import com.management.hospital.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    
    public CustomUserDetailsService(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.trace("Looking for user for {}", username);
        Optional<User> user = userRepository.findByName(username);
        if (!user.isPresent()) {
            LOGGER.info("USER NOT PRESENT for {} {}", username, user);
            throw new UsernameNotFoundException("user not found");
        }
        LOGGER.info("Found user for " + username +" : "+ user);
        return user.get();
    }
}
