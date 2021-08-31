package com.hospital.baronic.mapper;

import com.hospital.baronic.web.dto.PatientResponseDto;
import com.hospital.baronic.web.dto.PatientSaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PatientMapper {

    void insertPatientInfo(PatientSaveRequestDto patientSaveRequestDto) throws Exception;

    List<PatientResponseDto> getAllPatientList() throws Exception;

}
