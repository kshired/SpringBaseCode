package com.kshired.springbase.user.service;

import com.kshired.springbase.common.exception.NotFoundException;
import com.kshired.springbase.user.entity.User;
import com.kshired.springbase.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email : " + email));
    }
}
