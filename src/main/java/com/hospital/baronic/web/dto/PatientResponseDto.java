package com.hospital.baronic.web.dto;

import com.hospital.baronic.domain.patient.Patient;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class PatientResponseDto {
    private int chart_id;
    private String name;
    private String insert_time;
    private String update_time;
}
