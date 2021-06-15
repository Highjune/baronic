package com.hospital.baronic.domain.patient;

import lombok.*;

import javax.persistence.*;

// @Setter를 남발 금지
@NoArgsConstructor
@Getter
public class Patient {

    private int chart_Id;
    private String name;

    public Patient(int chartId, String name){
        this.chart_Id = chartId;
        this.name = name;
    }
}