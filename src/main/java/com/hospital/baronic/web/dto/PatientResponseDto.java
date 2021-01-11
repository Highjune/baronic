package com.hospital.baronic.web.dto;

import com.hospital.baronic.domain.patient.Patient;
import lombok.Getter;

@Getter
public class PatientResponseDto {
    private String name;
    private String gender;
    private int age;
    private String diagnosis_content;

//    public PatientResponseDto(Patient entity){
//        this.name = entity.getName();
//        this.gender = entity.getGender();
//        this.age = entity.getAge();
//        this.diagnosis_content = entity.getDiagnosis_content();
//    }
}
