package com.hospital.baronic.mapper;

import com.hospital.baronic.web.dto.PatientSaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatientMapper {

    void insertPatientInfo(PatientSaveRequestDto patientSaveRequestDto) throws Exception;
}
