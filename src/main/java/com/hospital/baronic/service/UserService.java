package com.hospital.baronic.service;

import com.hospital.baronic.domain.patient.PatientMapper;
import com.hospital.baronic.domain.user.User;
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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private static final int SALT_SIZE = 16;

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // register
    public String userRegister (UserDto userDto) throws Exception {
        boolean idChk = this.isAlreadyID(userDto); // check already id?
        if (idChk == true) {
            return "failure, there is already id";
        }
        boolean pNumChk = this.isAlreadyPNum(userDto);
        if (pNumChk == true) {
            return "failure, there is already phone_num";
        }

        try {
            String salt = this.getSALT();
            byte[] bytePasswd = userDto.getPasswd().getBytes();
            String hashPasswd = this.Hashing(bytePasswd, salt);
            User newUser = new User(userDto.getId(), hashPasswd, salt, userDto.getPhone_num());
            this.userMapper.registerUser(newUser);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public boolean login (UserDto userDto) throws Exception {
        String id = userDto.getId();
        UserDto alreadyUser = this.userMapper.isAlreadyID(id);
        if (alreadyUser == null) { // if user doesn't exist
//            return "No user";
            return false;
        }

        String salt = this.get_SALT(userDto);
        String hashPasswd = this.Hashing(userDto.getPasswd().getBytes(), salt);

        if (alreadyUser.getPasswd().equals(hashPasswd)) {
//            return "login success";
            return true;
        } else {
//            return "login fail";
            return false;
        }
    }

    public void updateSessionValue(Map<String, Object> params) throws Exception {
        this.userMapper.updateSessionValue(params);
    }

    // check already id (when register)
    private boolean isAlreadyID(UserDto userDto) throws Exception {
        String id = userDto.getId();
        UserDto alreadyUser = this.userMapper.isAlreadyID(id);
        return Optional.ofNullable(alreadyUser).isPresent();
    }

    // check already phone Number (when register)
    private boolean isAlreadyPNum(UserDto userDto) throws Exception {
        String phone_num = userDto.getPhone_num();
        List<UserDto> isAlreadyPNum = this.userMapper.isAlreadyPNum(phone_num);
        if (isAlreadyPNum.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    // find salt value of id (when login)
    public String get_SALT(UserDto userDto) throws Exception {
        String id = userDto.getId();
        String err = null; // If id doesn't exist
        err = this.userMapper.get_SALT(id);
        return err;
    }

    private String getSALT() throws Exception {
        SecureRandom rnd = new SecureRandom();
        byte[] temp = new byte[SALT_SIZE];
        rnd.nextBytes(temp);

        return Byte_to_String(temp);
    }

    // change byte value to 16binary
    private String Byte_to_String(byte[] temp) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (byte a : temp) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }

    // hashing password
    private String Hashing(byte[] password, String Salt) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-256"); // SHA-256

        // key-stretching
        for (int i = 0 ; i < 10000 ; i++) {
            String temp = Byte_to_String(password) + Salt;
            md.update(temp.getBytes());
            password = md.digest();
        }

        return Byte_to_String(password);
    }

}
