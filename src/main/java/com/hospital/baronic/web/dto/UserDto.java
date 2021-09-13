package com.hospital.baronic.web.dto;

import lombok.Data;

@Data
public class UserDto {
    String id;
    String passwd;
    String salt;
    String phone_num;
    String sessionId;
    int is_valid;
}
