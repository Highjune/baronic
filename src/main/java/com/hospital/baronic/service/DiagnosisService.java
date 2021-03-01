package com.hospital.baronic.service;

import com.hospital.baronic.domain.Diagnosis.Diagnosis;
import com.hospital.baronic.domain.Diagnosis.DiagnosisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosisService {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    // M) diag_date를 Date형으로 변환하기
    public int insertDiagnosisSchedule(String diag_date, String diag_content) {
        Diagnosis diagnosis = new Diagnosis(diag_date, diag_content); // chart_id 는 fk로 설정
        this.diagnosisRepository.save(diagnosis);
        return diagnosis.getReservation_id();
    }

    public List<Diagnosis> getAllReservations() {
        return this.diagnosisRepository.findAll();
    }

}
