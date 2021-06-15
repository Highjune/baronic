package com.hospital.baronic.service;

import com.hospital.baronic.domain.patient.Patient;
import com.hospital.baronic.mapper.PatientMapper;
import com.hospital.baronic.web.dto.PatientSaveRequestDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientMapper patientMapper;

    public PatientService(PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    //엑셀 환자 데이터 저장
    public void insertPatientInfo(int chart_Id, String name) throws Exception {
        PatientSaveRequestDto patientSaveRequestDto = new PatientSaveRequestDto(chart_Id, name);
        patientMapper.insertPatientInfo(patientSaveRequestDto);
    }

//    public List<Patient> getAllReservations() {
//        return this.patientRepository.findAll();
//    }

}
