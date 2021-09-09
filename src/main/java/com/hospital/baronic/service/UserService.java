package com.hospital.baronic.service;

import com.hospital.baronic.domain.patient.PatientMapper;
import com.hospital.baronic.domain.user.UserMapper;
import com.hospital.baronic.web.dto.PatientResponseDto;
import com.hospital.baronic.web.dto.PatientSaveRequestDto;
import com.hospital.baronic.web.dto.UserDto;
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

    // 회원가입
    public String userRegister (UserDto userDto) {
        isAlreadyID(userDto.getId());

        return null;
    }

    // 기존 아이디 있는지 체크
    public static boolean isAlreadyID(String id) {

        return false;
    }

    // 해당 아이디의 SALT 값 찾기
    public static String get_SALT(String id) {

    }



}
