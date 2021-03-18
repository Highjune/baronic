package com.hospital.baronic.domain.Diagnosis;

import com.hospital.baronic.domain.patient.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity(name="Reservation")
public class Reservation extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id // pk
    private int reservation_id;

    //foreign key
//    @Column(columnDefinition = "TEXT", nullable = false) // fk 처리하고 나서는 다시 넣기
//    private int chart_id;

    // 진료날짜
    @Column //
    private Date reservation_date;
//    private String reservation_date;

    //진료 내용
    @Column
    private String reservation_content;

    @Column // 0(invalid), 1(valid)
    private int is_valid;

//    @Builder
//    public Reservation(String reservation_date, String reservation_content){
//        super();
//        this.reservation_date = reservation_date;
//        this.reservation_content = reservation_content;
//    }

    @Builder
    public Reservation(Date reservation_date, String reservation_content){
        super();
        this.reservation_date = reservation_date;
        this.reservation_content = reservation_content;
    }
}
