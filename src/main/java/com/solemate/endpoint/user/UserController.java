package com.solemate.endpoint.user;

import com.solemate.endpoint.user.dto.UserLoginRequestDto;
import com.solemate.endpoint.user.dto.UserResponseDto;
import com.solemate.endpoint.user.dto.UserSaveRequestDto;
import com.solemate.endpoint.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("user")
@RestController
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/join")
    public Boolean save(@RequestBody UserSaveRequestDto requestDto){
        return userService.save(requestDto);
    }

    @PostMapping("/login")
    public UserResponseDto login(@RequestBody UserLoginRequestDto requestDto){
        return userService.login(requestDto);
    }
}
