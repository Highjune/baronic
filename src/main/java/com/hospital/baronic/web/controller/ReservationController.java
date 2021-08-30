package com.hospital.baronic.web.controller;

import com.hospital.baronic.service.ReservationService;
import com.hospital.baronic.web.dto.ReservationResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/insertReservationExcelData") // column 먼저 읽기(세로로)
    public String insertReservationExcelData() {
        String ans = "";
        ans = this.reservationService.parseReservationExcelData();
        return ans;
    }

    @GetMapping("/getReservationList")
    public List<ReservationResponseDto> getReservationList() throws Exception {
        return this.reservationService.getReservationList();
    }
}
