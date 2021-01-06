package com.hospital.baronic.domain.patient;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="patient") // @Entity가 붙은 클래스는 JPA가 관리하는 클래스이고, 테이블과 매핑할 테이블은 이 어노테이션 붙인다.
public class Patient {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment(자동생성) 만들어줌
    private int id;

    @Column // 사실 필드마다 안 붙여도 된다.
    private String name;

    private String gender;

    private int age;

    private String diagnosis_content;

    @Builder
    public Patient(String name, String gender, int age, String diagnosis_content){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.diagnosis_content = diagnosis_content;
    }

}
