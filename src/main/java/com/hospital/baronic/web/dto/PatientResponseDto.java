package com.hospital.baronic.web.dto;

import com.hospital.baronic.domain.patient.Patient;
import lombok.Data;
import lombok.Getter;

@Data
public class PatientResponseDto {
    private int chart_Id;
    private String name;

}
