package com.hospital.baronic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 이 프로젝트의 메인 클래스
// @SpringBootApplication로 인해 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
// @SpringBootApplication이 있는 위치부터 설정을 읽어가므로 항상 프로젝트의 최상단에 위치해야만 함
//@EnableJpaAuditing // JPA Auditing 어노테이션을 활성화할 수 있도록 Application 클래스에 활성화 어노테이션 추가

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // SpringApplication.run이 내장 was 실행
        // 내장 was로 인해 항상 서버에 tomcat을 설치할 필요가 없고, 스프링 부트로 만들어진 .jar(실행 가능한 java 패키지 파일)로 실행하면 됨
        SpringApplication.run(Application.class, args);
    }
}
