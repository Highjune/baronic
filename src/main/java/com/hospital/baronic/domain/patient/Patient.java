package com.hospital.baronic.domain.patient;

import lombok.*;

import javax.persistence.*;


// @Setter를 남발 금지
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity(name="patient")
public class Patient {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column(length = 500, nullable = false)
    private String gender;

    @Column
    private int age;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String diagnosis_content;

//    @Builder
//    public Patient(String name, String gender, int age, String diagnosis_content){
//        this.name = name;
//        this.gender = gender;
//        this.age = age;
//        this.diagnosis_content = diagnosis_content;
//    }

    public Patient(String name, String gender, int age, String diagnosis_content){
        super();
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.diagnosis_content = diagnosis_content;
    }
}