package com.hospital.baronic.domain.Diagnosis;

import com.hospital.baronic.domain.patient.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity(name="Diagnosis")
public class Diagnosis extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id // pk
    private int reservation_id;

    //foreign key
//    @Column(columnDefinition = "TEXT", nullable = false) // fk 처리하고 나서는 다시 넣기
    private int chart_id;

    // 진료날짜
    @Column // M) diag_date를 Date로 변환하기
    private String diag_date;
//    private Date diag_date;

    //진료 내용
    @Column
    private String diag_content;

    @Column // 0(invalid), 1(valid)
    private int is_valid;

    @Builder
    public Diagnosis(String diag_date, String diag_content){
        super();
        this.diag_date = diag_date;
        this.diag_content = diag_content;
    }


}