package com.hospital.baronic.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
public class ReservationResponseDto {
    private int reservation_id;
    private String patient_name;
    private int chart_Id;
    private String todo;
    private Date reservation_date;
    private Date reservation_date_to;
    private int is_valid;
    private int is_modifying;
    private String dump;
    private int position;
    private String owner;
    private Date insert_time;
    private Date update_time;

}
