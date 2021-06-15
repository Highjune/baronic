package com.hospital.baronic.web;

import com.hospital.baronic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/insertPatientInfo")
    public String insertPatientInfo() {
        String ans = "";
        ans = this.patientService.insertPatientInfo();
        return ans;
    }
}