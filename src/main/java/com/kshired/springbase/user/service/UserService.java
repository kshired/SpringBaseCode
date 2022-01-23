package com.kshired.springbase.user.service;

import com.kshired.springbase.user.dto.JwtResponseDto;
import com.kshired.springbase.user.dto.UserResponseDto;
import com.kshired.springbase.user.dto.UserRequestDto;

public interface UserService {
    UserResponseDto signUp(UserRequestDto userRequestDto);
    JwtResponseDto login(UserRequestDto userRequestDto);
}
