package com.hospital.baronic.web.controller;

import com.hospital.baronic.service.UserService;
import com.hospital.baronic.web.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @RequestMapping(value = "/register/create", method={RequestMethod.POST})
    public String userRegister (@RequestBody UserDto userDto) {
        String ans = "";
        ans =  this.userService.userRegister(userDto);

        return ans;

    }

}
