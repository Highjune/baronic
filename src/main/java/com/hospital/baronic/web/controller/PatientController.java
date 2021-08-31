package com.hospital.baronic.web.controller;

import com.hospital.baronic.service.PatientService;
import com.hospital.baronic.web.dto.PatientResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/insertPatientInfo")
    public String insertPatientInfo() {
        String ans = "";
        ans = this.patientService.insertPatientInfo();
        return ans;
    }

    @GetMapping("/getAllPatientList")
    public List<PatientResponseDto> getAllPatientList() throws Exception {
        return this.patientService.getAllPatientList();
    }
}