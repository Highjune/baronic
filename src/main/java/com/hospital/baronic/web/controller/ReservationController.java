package com.hospital.baronic.web.controller;

import com.hospital.baronic.service.ReservationService;
import com.hospital.baronic.web.dto.ReservationResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/insertReservationExcelData", method={RequestMethod.GET}) // column 먼저 읽기(세로로)
    public String insertReservationExcelData() {
        String ans = "";
        ans = this.reservationService.parseReservationExcelData();
        return ans;
    }

    @RequestMapping(value = "/getAllReservationList", method={RequestMethod.GET})
    public List<ReservationResponseDto> getAllReservationList() throws Exception {
        return this.reservationService.getAllReservationList();
    }
}
