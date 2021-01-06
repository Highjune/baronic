package com.hospital.baronic.controller;

import com.hospital.baronic.dto.PatientDto;
import com.hospital.baronic.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ReservationController {
    // @Autowired는 스프링이 관리하는 빈(Bena)을 주입 받게 해준다.
    @Autowired
    ReservationService reservationService;

    // 환자 1명 정보 등록하기(map으로 정보받아서 그대로 map으로 service로 넘기기)
    // 객체로 말고 map으로 받아서 service로 넘긴다 -> 그런데 이게 jpa할 때도 이렇게 할 수 있는지 확인해 봐야 함
    @PostMapping("/reservation/addOnePatient")
    public void reservePatientInfo(@RequestBody Map<String, Object> params) throws Exception {
        reservationService.addOnePatient(params);
    }

    // 다른 방법
    // 환자 1명 정보 등록하기(하나하나 받아서 객체로 service로 넘기기)
    // 메서드의 파라미터에 있는 @RequestParam은 외부(client)에서 API로 넘긴 파라미터를 가져오는 어노테이션(프런트에서 보내주는 값들)
//    @PostMapping("/reservation/addOnePatient1")
//    public void reservePatientInfo1(@RequestParam(value = "name", required = false, defaultValue = "default_name") String name,
//                                    @RequestParam(value = "gender", required = false, defaultValue = "default_gender") String gender,
//                                    @RequestParam(value = "age", required = false) int age,
//                                    @RequestParam(value = "diagnosis_content", required = false, defaultValue = "default_diagnosis") String diagnosis_content)
//                                    throws Exception {
//        PatientDto pd = new PatientDto(name, gender, age, diagnosis_content);
//        reservationService.addOnePatient1(pd);
//    }
}