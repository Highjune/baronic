package com.hospital.baronic.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatientUpdateRequestDto {
    private String name;
    private String gender;
    private int age;
    private String diagnosis_content;

    @Builder
    public PatientUpdateRequestDto(String name, String gender, int age, String diagnosis_content){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.diagnosis_content = diagnosis_content;
    }
}