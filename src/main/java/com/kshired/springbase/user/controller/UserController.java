package com.kshired.springbase.user.controller;

import com.kshired.springbase.common.utils.ApiUtils.ApiResult;
import com.kshired.springbase.user.dto.UserResponseDto;
import com.kshired.springbase.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static com.kshired.springbase.common.utils.ApiUtils.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @GetMapping("/info")
    public ApiResult<UserResponseDto> findUserInfoById(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return success(user.toUserResponseDto());
    }
}
