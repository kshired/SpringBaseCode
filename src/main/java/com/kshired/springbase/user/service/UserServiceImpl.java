package com.kshired.springbase.user.service;

import com.kshired.springbase.user.dto.UserResponseDto;
import com.kshired.springbase.user.dto.UserSignUpDto;
import com.kshired.springbase.user.entity.User;
import com.kshired.springbase.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto signUp(UserSignUpDto userSignUpDto) {
        String email = userSignUpDto.getEmail();
        String password = userSignUpDto.getPassword();
        User user = userRepository.save(new User(email, password));
        return user.toUserResponseDto();
    }
}
