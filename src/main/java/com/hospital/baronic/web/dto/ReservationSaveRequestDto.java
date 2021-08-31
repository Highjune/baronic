package com.hospital.baronic.web.dto;

import lombok.*;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationSaveRequestDto {
    private String patient_name;
    private int chart_Id;
    private String todo;
    private Date reservation_date;
    private int is_valid;
    private int is_modifying;
    private String dump;
    private int position;

    public ReservationSaveRequestDto(String patient_name, int chart_id, String todo, String dump, Date reservation_date, int position) {
        this.patient_name = patient_name;
        this.chart_Id = chart_id;
        this.todo = todo;
        this.dump = dump;
        this.reservation_date = reservation_date;
        this.position = position;
    }
}

