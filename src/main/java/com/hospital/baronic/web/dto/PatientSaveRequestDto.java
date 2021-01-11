package com.hospital.baronic.web.dto;

import com.hospital.baronic.domain.patient.Patient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//domain에 있는 Patient(이건 Entity클래스) 와 거의 유사하지만 다름
@Getter
@Setter
@NoArgsConstructor
public class PatientSaveRequestDto {
    private String name;
    private String gender;
    private int age;
    private String diagnosis_content;

    @Builder
    public PatientSaveRequestDto(String name, String gender, int age, String diagnosis_content){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.diagnosis_content = diagnosis_content;
    }

//    public Patient toEntity(){
//        return Patient.builder()
//                .name(name)
//                .gender(gender)
//                .age(age)
//                .diagnosis_content(diagnosis_content)
//                .build();
//    }
}
