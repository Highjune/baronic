package com.hospital.baronic.service;

import com.hospital.baronic.dto.PatientDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService {

    public void addOnePatient(Map<String, Object> params) throws Exception{
        // Patient 객체 정보를 db로 입력~~ (by jpa)
    }

    public void addOnePatient1(PatientDto pd) throws Exception{
        // Patient Map 정보를 db로 입력~~ (by jpa)
    }
}