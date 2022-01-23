package com.kshired.springbase.user.controller;

import com.kshired.springbase.common.utils.ApiUtils;
import com.kshired.springbase.user.dto.JwtResponseDto;
import com.kshired.springbase.user.dto.UserRequestDto;
import com.kshired.springbase.user.dto.UserResponseDto;
import com.kshired.springbase.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kshired.springbase.common.utils.ApiUtils.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public ApiUtils.ApiResult<UserResponseDto> signup(@RequestBody UserRequestDto userRequestDto){
        return success(userService.signUp(userRequestDto));
    }

    @PostMapping("/login")
    public ApiUtils.ApiResult<JwtResponseDto> login(@RequestBody UserRequestDto userRequestDto){
        return success(userService.login(userRequestDto));
    }
}