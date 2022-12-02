package com.hospital.baronic.web.controller;

import com.hospital.baronic.define.ResponseCode;
import com.hospital.baronic.domain.user.User;
import com.hospital.baronic.model.ResponseModel;
import com.hospital.baronic.service.UserService;
import com.hospital.baronic.web.SessionConstants;
import com.hospital.baronic.web.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // register
    @RequestMapping(value = "/register/create", method = {RequestMethod.POST})
    public String userRegister(@RequestBody UserDto userDto) throws Exception {
        String res = this.userService.userRegister(userDto);
        if (res.equalsIgnoreCase("success")) {
            return "success";
        } else {
            return res;
        }
    }

    // login
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login(@RequestBody UserDto userDto
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
        Map<String, Object> params = new HashMap<>();
        String id = userDto.getId();
        params.put("id", id);
        HttpSession session = request.getSession();

        boolean loginCheck = this.userService.login(userDto);
        if (loginCheck == true) {
            String sessionId = UUID.randomUUID().toString();
            params.put("sessionId", sessionId);
            this.userService.updateSessionValue(params);

            Cookie cookie = new Cookie("sessionId", sessionId);
            cookie.setMaxAge(-1);
            response.addCookie(cookie);

            return "login success";
        } else {
            return "login fail";
        }
    }

    @RequestMapping(value = "logout", method = {RequestMethod.POST})
    public String logout(HttpServletRequest request) throws Exception {
        String sessionId = "";

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
//              System.out.println(cookie.getName() + ": " + cookie.getValue());
                if (cookie.getName().equals("sessionId")) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
//        boolean isSessionIdValid = this.userService.checkSessionId(sessionId);
        boolean result = this.userService.logout(sessionId);
        if (result == true) {
            return "logout success";
        } else {
            return "logoug failure";
        }
    }


}
