package com.hospital.baronic.domain.Reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Reservation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id // pk
    private int reservation_id;

    @Column
    private String patient_name;

    @Column
    private int chart_id; // 나중에 fk 처리하기

    @Column
    private String todo;

    // 진료날짜
    @Column //
    private Date reservation_date;

    @Column // 0(invalid), 1(valid)
    private int is_valid;

    @Column
    private int is_modifying;

    @Column
    private String dump;

    @Column
    private int position;

    @Builder
    public Reservation(String patient_name, int chart_id, String todo, String dump, Date reservation_date, int position){
        super();
        this.patient_name = patient_name;
        this.chart_id = chart_id;
        this.todo = todo;
        this.dump = dump;
        this.reservation_date = reservation_date;
        this.position = position;
    }

    @Builder
    public Reservation(String patient_name, int chart_id, String todo, String dump) {
        super();
        this.patient_name = patient_name;
        this.chart_id = chart_id;
        this.todo = todo;
        this.dump = dump;
    }

    @Builder
    public Reservation(int chart_id, String todo, String dump) {
        super();
        this.chart_id = chart_id;
        this.todo = todo;
        this.dump = dump;
    }

}
