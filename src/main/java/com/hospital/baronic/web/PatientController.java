package com.hospital.baronic.web;

import com.hospital.baronic.domain.patient.Patient;
import com.hospital.baronic.domain.patient.PatientRepository;
import com.hospital.baronic.service.PatientService;
import com.hospital.baronic.web.dto.PatientSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PatientController {
    // @Autowired는 스프링이 관리하는 빈(Bena)을 주입 받게 해준다.
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientservice;

    // 환자 1명 정보 등록하기(map으로 정보받아서 그대로 map으로 service로 넘기기)
    // 객체로 말고 map으로 받아서 service로 넘긴다 -> 그런데 이게 jpa할 때도 이렇게 할 수 있는지 확인해 봐야 함
    @PostMapping("/reservation/addOnePatient")
    public Map<String, Object> reservePatientInfo(@RequestBody PatientSaveRequestDto patientSaveRequestdto) throws Exception {
        //PatientSaveRequestDto를 받아와서(정보 들고있음) get으로 필드 저장값 꺼낸 후
        //Patient 생성 후에 정보를 담고 나서 바로 jpa로 저장
        //save파라미터 안에 builder(=생성자)로 입력
//        this.patientRepository.save(new Patient(patientSaveRequestdto.getName("june"));
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Pateient p = new Patient(dto.getName()..);
        boolean a;
        a = false;
//        a = patientservice.checkreservation(p);
        if (a) {
            //저장
//            this.patientRepository.save(p);
        } else {
            String ansewr = "already exit";

            resultMap.put("result", ansewr);
            return resultMap;
        }
        return resultMap;
    }

    @PostMapping("/resrvation/deletePatient")
    public Map<String, Object> deletePatient(@RequestBody PatientDeleteRequestDto patientDeleteRequestDto) throws Exception {
        // 이름, 시간
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Pateient p = new Patient(dto.getName()..);
        boolean a;
        Pair<boolean, String> resultPair;
        a = false;
        resultPair = patientservice.deleteReservation(Patient p);
        if(resultPair.first) {
            resultMap.put("result", "success");
        } else {
             resultMap.put("result", resultPair.second);

        }
        return resultMap;
    }

    @PostMapping("/reservation/showReservationForDate")
    public List<Map<String, Object>> showReservationForDate(@RequestBody DateDto dateDto) throws Exception {
        return patientservice.showReservationForDate(DateDto.getDate());
    }
}