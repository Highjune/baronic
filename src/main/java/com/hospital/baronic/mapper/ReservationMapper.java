package com.hospital.baronic.mapper;

import com.hospital.baronic.web.dto.PatientSaveRequestDto;
import com.hospital.baronic.web.dto.ReservationSaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

    void insertReservationExcelData(ReservationSaveRequestDto reservationSaveRequestDto) throws Exception;
}
