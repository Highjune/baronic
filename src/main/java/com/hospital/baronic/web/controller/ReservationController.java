package com.hospital.baronic.web.controller;

import com.hospital.baronic.service.ReservationService;
import com.hospital.baronic.service.UserService;
import com.hospital.baronic.web.dto.ReservationResponseDto;
import com.hospital.baronic.web.dto.UserDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/api/v1")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;

    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    // excel data -> insert DB
    @RequestMapping(value = "/insertReservationExcelData", method={RequestMethod.GET}) // column 먼저 읽기(세로로)
    public String insertReservationExcelData() {
        String ans = "";
        ans = this.reservationService.parseReservationExcelData();
        return ans;
    }

    // api test
    @RequestMapping(value = "/getAllReservationList", method={RequestMethod.GET})
    public List<ReservationResponseDto> getAllReservationList() throws Exception {
        return this.reservationService.getAllReservationList();
    }

    @RequestMapping(value = "/reserve/create", method={RequestMethod.GET})
    public String reserveCreate(@RequestBody UserDto userDto
            , HttpServletRequest request) throws Exception {
        String sessionId = "";
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("sessionId")) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
        if (!Optional.ofNullable(this.userService.checkSessionId(sessionId)).isPresent()) {
            return "invalid session";
        }



    }


}
