package com.hospital.baronic.web.controller;

import com.hospital.baronic.define.ResponseCode;
import com.hospital.baronic.domain.user.User;
import com.hospital.baronic.model.ResponseModel;
import com.hospital.baronic.service.UserService;
import com.hospital.baronic.web.SessionConstants;
import com.hospital.baronic.web.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // register
    @RequestMapping(value = "/register/create", method={RequestMethod.POST})
    public String userRegister (@RequestBody UserDto userDto) throws Exception {
        String res = this.userService.userRegister(userDto);
        if (res.equalsIgnoreCase("success")) {
            return "success";
        } else {
            return res;
        }
    }

    // login
    @RequestMapping(value = "/login", method={RequestMethod.POST})
    public String login (@RequestBody UserDto userDto
    , HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();

        //uuid = UUID.randomUUID().toString();// 전 세계에서 유일한 값이 나옴.

        //User loginUser = this.userService.login(userDto);
//        if (res.equalsIgnoreCase("success")) {
//            session.setAttribute(SessionConstants.LOGIN_MEMBER, loginUser);
//
//            return "success";
//
//
//
//        } else {
//            return res;
//        }
//    }

        return null;
}
