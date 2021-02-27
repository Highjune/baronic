package com.hospital.baronic.domain.patient;

import lombok.*;

import javax.persistence.*;

// @Setter를 남발 금지
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity(name="patient")
// 이렇게 앞으로 추가될 엔티티들은 등록일/수정일은 고민할 필요x. BasetimeEntity만 상속받으면 자동으로 해결된다.
public class Patient extends BaseTimeEntity{
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Id // pk
    private int chart_Id;

    @Column
    private String name;

//    @Column(length = 500, nullable = false)
//    private String gender;

//    @Column
//    private int age;

//    @Column(columnDefinition = "TEXT", nullable = false)
//    private String diagnosis_content;

//    @Builder
//    public Patient(String name, String gender, int age, String diagnosis_content){
//        this.name = name;
//        this.gender = gender;
//        this.age = age;
//        this.diagnosis_content = diagnosis_content;
//    }

//    public Patient(String name, String gender, int age, String diagnosis_content){
//        super();
//        this.name = name;
//        this.gender = gender;
//        this.age = age;
//        this.diagnosis_content = diagnosis_content;
//    }

//    public Patient(int chartId, String name){
//        super();
//        this.chartId = chartId;
//        this.name = name;
//    }
}