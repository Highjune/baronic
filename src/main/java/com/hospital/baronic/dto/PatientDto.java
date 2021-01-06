package com.hospital.baronic.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    private String name;
    private String gender;
    private int age;
    private String diagnosis_content;

}
