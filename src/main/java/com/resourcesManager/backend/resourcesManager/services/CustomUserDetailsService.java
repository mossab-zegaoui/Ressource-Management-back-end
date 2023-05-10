package com.resourcesManager.backend.resourcesManager.services;

import com.resourcesManager.backend.resourcesManager.mappers.MapUserToUserDetails;
import com.resourcesManager.backend.resourcesManager.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MapUserToUserDetails(userRepository.findUserByUsername(username));

    }
}
