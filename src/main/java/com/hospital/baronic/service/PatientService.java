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
@RequiredArgsConstructor
public class PatientService {

    @Autowired
    private PatientMapper patientMapper;

    public void insertPatientInfo(int chart_Id, String name) throws Exception {
        PatientSaveRequestDto patientSaveRequestDto = new PatientSaveRequestDto(chart_Id, name);
        patientMapper.insertPatientInfo(patientSaveRequestDto);
    }

//    public List<Patient> getAllReservations() {
//        return this.patientRepository.findAll();
//    }

}
