package com.hospital.baronic.service;

import com.hospital.baronic.domain.patient.Patient;
import com.hospital.baronic.domain.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

//    public int addTestReservation(String name, String gender, int age, String diagnosis_content) {
//        Patient patient = new Patient(name, gender, age, diagnosis_content);
//        this.patientRepository.save(patient); // save에 커서 두고 ctrl + b (호출하는메서드 찾아감) 눌러보면 jpa기본 CRUD다 볼수있음
//        return patient.getId();

    public int addTestReservation(int chart_Id, String name) {
        Patient patient = new Patient(chart_Id, name);
        this.patientRepository.save(patient); // save에 커서 두고 ctrl + b (호출하는메서드 찾아감) 눌러보면 jpa기본 CRUD다 볼수있음
        return patient.getChart_Id();
    }

    public List<Patient> getAllReservations() {
        return this.patientRepository.findAll();
    }

}
