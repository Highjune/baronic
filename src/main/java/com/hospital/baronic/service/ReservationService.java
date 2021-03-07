package com.hospital.baronic.service;

import com.hospital.baronic.domain.Diagnosis.Reservation;
import com.hospital.baronic.domain.Diagnosis.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // M) diag_date를 Date형으로 변환하기
    public int insertReservationSchedule(String diag_date, String diag_content) {
        Reservation reservation = new Reservation(diag_date, diag_content); // chart_id 는 fk로 설정
        this.reservationRepository.save(reservation);
        return reservation.getReservation_id();
    }

    public List<Reservation> getAllReservations() {
        return this.reservationRepository.findAll();
    }

}
