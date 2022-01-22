package com.kshired.springbase.user.service;

import com.kshired.springbase.user.dto.UserResponseDto;
import com.kshired.springbase.user.dto.UserSignUpDto;

public interface UserService {
    UserResponseDto signUp(UserSignUpDto userSignUpDto);
}
