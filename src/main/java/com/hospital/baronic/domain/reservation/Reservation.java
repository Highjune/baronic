package com.hospital.baronic.domain.reservation;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Reservation {

    private int reservation_id;
    private String patient_name;
    private int chart_id; // 나중에 fk 처리하기
    private String todo;
    private Date reservation_date;
    private int is_valid; // 0(invalid), 1(valid)
    private int is_modifying;
    private String dump;
    private int position;

    @Builder
    public Reservation(String patient_name, int chart_id, String todo, String dump, Date reservation_date, int position){
        this.patient_name = patient_name;
        this.chart_id = chart_id;
        this.todo = todo;
        this.dump = dump;
        this.reservation_date = reservation_date;
        this.position = position;
    }

    @Builder
    public Reservation(String patient_name, int chart_id, String todo, String dump) {
        this.patient_name = patient_name;
        this.chart_id = chart_id;
        this.todo = todo;
        this.dump = dump;
    }

    @Builder
    public Reservation(int chart_id, String todo, String dump) {
        this.chart_id = chart_id;
        this.todo = todo;
        this.dump = dump;
    }

}
