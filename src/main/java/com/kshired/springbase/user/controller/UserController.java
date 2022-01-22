package com.kshired.springbase.user.controller;

import com.kshired.springbase.common.utils.ApiUtils;
import com.kshired.springbase.common.utils.ApiUtils.ApiResult;
import com.kshired.springbase.user.dto.UserResponseDto;
import com.kshired.springbase.user.dto.UserSignUpDto;
import com.kshired.springbase.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kshired.springbase.common.utils.ApiUtils.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ApiResult<UserResponseDto> signup(@RequestBody UserSignUpDto userSignUpDto){
        return success(userService.signUp(userSignUpDto));
    }
}
