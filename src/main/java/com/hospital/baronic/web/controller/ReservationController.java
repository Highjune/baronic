package com.hospital.baronic.web.controller;

import com.hospital.baronic.domain.reservation.Reservation;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
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

    @RequestMapping(value = "/reserve/create", method={RequestMethod.POST})
    public int reserveCreate(@RequestBody Map<String, Object> params
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
            if (!this.userService.checkSessionId(sessionId)) {
    //            return "invalid session";
                return -1;
            }

        String name = (String) params.get("name");
        String todo = (String) params.get("todo");

        // String -> Date
        String str_reservation_date_to = (String)params.get("dateTo");
        SimpleDateFormat dateTypeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date reservation_date_to = new Date(dateTypeFormat.parse(str_reservation_date_to).getTime());

        Reservation reservation = new Reservation();
        reservation.setPatient_name(name);
        reservation.setChart_id(-1);
        reservation.setTodo(todo);
        reservation.setReservation_date_to(reservation_date_to);
        reservation.setOwner(sessionId);

        int result = this.reservationService.reserveCreate(reservation);

        return result;
    }

}
