package com.hospital.baronic.domain.patient;

import lombok.*;

import javax.persistence.*;

// @Setter를 남발하지 말자(나중에 어디서 값을 막 넣었는지 헷갈림)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="patient") // @Entity가 붙은 클래스는 JPA가 관리하는 클래스이고(테이블과 링크될 클래스), 테이블과 매핑할 테이블은 이 어노테이션 붙인다.
//기본적으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭한다.
//SalesaMager.java -> sales_manager.table
public class Patient {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk생성 규칙을 말함. auto_increment(자동생성) 만들어줌
    private int id;

    // @Column 어노테이션은 굳이 안해도 되는데 기본값 이외에 값을 추가하고자 할 때 씀(ex. 문자열의 경우 VARCHAR(255)가 기본값)
    @Column
    private String name;

    @Column(length = 500, nullable = false) // 그냥 길이 500으로 해봄
    private String gender;

    @Column
    private int age;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String diagnosis_content;

    // DB에 넣는 것
    // 원래의 기본적 구조 : 생성자를 통해 최종값을 채운 후 DB에 삽입. 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경하는 것잊 ㅓㄴ제
    // 더 좋은 방법(기존과 거의 동일) : 생성자 대신에 @Builder를 통해 제공되는 빌더 클래스 사용. 생성자나 빌더나 생성 시점에 값을 채워주는 역할이 똑같다.
    // 그러면 왜 builder 방법임 : 생성자의 경우 채워야 할 필드가 무엇인지 명확히 지정할 수 없다. builder는 딱딱 명확해서 편함.
    // 여튼 똑같은 것
    // Setter 없이
    @Builder
    public Patient(String name, String gender, int age, String diagnosis_content){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.diagnosis_content = diagnosis_content;
    }

}
