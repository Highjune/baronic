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
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final int SALT_SIZE = 16;

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // register
    public String userRegister (UserDto userDto) throws Exception {
        boolean chk = this.isAlreadyID(userDto); // check is there already id
        if (chk == true) {
            return "failure, there is already id";
        }


        String salt = this.getSALT();


        return null;
    }

    // check is there already id (when register)
    private boolean isAlreadyID(UserDto userDto) throws Exception {
        String id = userDto.getId();
        UserDto alreadyUser = this.userMapper.isAlreadyID(id);
        return Optional.ofNullable(alreadyUser).isPresent();
    }

    // find salt value of id (when login)
//    public String get_SALT(String id) {
//
//    }

    private String getSALT() throws Exception {
        SecureRandom rnd = new SecureRandom();
        byte[] temp = new byte[SALT_SIZE];
        rnd.nextBytes(temp);

        return Byte_to_String(temp);
    }

    // change byte value to 16binary
    private String Byte_to_String(byte[] temp) {
        StringBuilder sb = new StringBuilder();
        for (byte a : temp) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }

}
