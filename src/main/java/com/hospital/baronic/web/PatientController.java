package com.hospital.baronic.web;

import com.hospital.baronic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PatientController {
    // @Autowired는 스프링이 관리하는 빈(Bena)을 주입 받게 해준다.
    @Autowired
    PatientService reservationService;

    // 환자 1명 정보 등록하기(map으로 정보받아서 그대로 map으로 service로 넘기기)
    // 객체로 말고 map으로 받아서 service로 넘긴다 -> 그런데 이게 jpa할 때도 이렇게 할 수 있는지 확인해 봐야 함
    @PostMapping("/reservation/addOnePatient")
    public void reservePatientInfo(@RequestBody Map<String, Object> params) throws Exception {
    }

}