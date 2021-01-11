package com.hospital.baronic.web;

import com.hospital.baronic.domain.patient.Patient;
import com.hospital.baronic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PatientController {
    // @Autowired는 스프링이 관리하는 빈(Bena)을 주입 받게 해준다.
    @Autowired
    PatientService reservationService; //이거 이름 이상! patientService 인지 reservation service 인지. naming issue!

    // 환자 1명 정보 등록하기(map으로 정보받아서 그대로 map으로 service로 넘기기)
    // 객체로 말고 map으로 받아서 service로 넘긴다 -> 그런데 이게 jpa할 때도 이렇게 할 수 있는지 확인해 봐야 함
    @PostMapping("/reservation/addOnePatient")
    public void reservePatientInfo(@RequestBody Map<String, Object> params) throws Exception {
    }

    @RequestMapping("/reservation/addBok")
    public int addBok() throws Exception {
        return this.reservationService.addTestReservation("youngbin", "male", 32, "wisdom tooth");
    }

    @RequestMapping("/reservation/addHeo")
    public int addHeo() throws Exception {
        return this.reservationService.addTestReservation("kangjun", "male", 33, "implant");
    }

    @RequestMapping("/reservation/addJoo")
    public int addJoo() throws Exception {
        return this.reservationService.addTestReservation("hosung", "male", 32, "cavity");
    }

    @RequestMapping("/reservation/addKim")
    public int addKim() throws Exception {
        return this.reservationService.addTestReservation("wooyoung", "male", 33, "scaling");
    }

    //@PostMapping 하면 안됨. why?
    //RequestMapping 해도 안됨. why?
    @GetMapping("/reservation/getAllReservations")
    public List<Patient> getAllReservations() throws Exception {
        return this.reservationService.getAllReservations();
    }

}