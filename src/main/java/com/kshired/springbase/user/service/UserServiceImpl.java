package com.kshired.springbase.user.service;

import com.kshired.springbase.common.config.JwtTokenProvider;
import com.kshired.springbase.common.exception.NotFoundException;
import com.kshired.springbase.common.exception.UnauthorizedException;
import com.kshired.springbase.user.dto.JwtResponseDto;
import com.kshired.springbase.user.dto.UserResponseDto;
import com.kshired.springbase.user.dto.UserRequestDto;
import com.kshired.springbase.user.entity.User;
import com.kshired.springbase.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto signUp(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());

        User user = userRepository.save(new User(email, encodedPassword, Collections.singletonList("ROLE_USER")));
        return user.toUserResponseDto();
    }

    @Override
    public JwtResponseDto login(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found with email : " + email));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException("Wrong password.");
        }

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
        return new JwtResponseDto(token);
    }
}
