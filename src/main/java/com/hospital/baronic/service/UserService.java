package com.hospital.baronic.service;

import com.hospital.baronic.domain.patient.PatientMapper;
import com.hospital.baronic.domain.user.UserMapper;
import com.hospital.baronic.web.dto.PatientResponseDto;
import com.hospital.baronic.web.dto.PatientSaveRequestDto;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

}
