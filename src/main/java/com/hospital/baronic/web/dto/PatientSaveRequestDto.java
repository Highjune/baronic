package com.hospital.baronic.web.dto;

import com.hospital.baronic.domain.patient.Patient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PatientSaveRequestDto {
    private int chart_Id;
    private String name;

    @Builder
    public PatientSaveRequestDto(int chart_Id, String name) {
        this.chart_Id = chart_Id;
        this.name = name;
    }

}
