package com.hospital.baronic.domain.reservation;

import com.hospital.baronic.domain.reservation.Reservation;
import com.hospital.baronic.web.dto.PatientResponseDto;
import com.hospital.baronic.web.dto.PatientSaveRequestDto;
import com.hospital.baronic.web.dto.ReservationResponseDto;
import com.hospital.baronic.web.dto.ReservationSaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Mapper
public interface ReservationMapper {

    void insertReservationExcelData(ReservationSaveRequestDto reservationSaveRequestDto) throws Exception;

    List<ReservationResponseDto> getAllReservationList() throws Exception;

    int reserveCreate(Reservation reservation) throws Exception;

    String getOwnerSessionId(int reservation_id) throws Exception;

    int reserveModify(Map<String, Object> params) throws Exception;
}
