package com.hospital.baronic.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class User {
    String id;
    String passwd;
    String salt;
    String phone_num;
    String sessionId;
    int is_valid;

    public User(String id, String passwd, String salt, String phone_num) {
        this.id = id;
        this.passwd = passwd;
        this.salt = salt;
        this.phone_num = phone_num;
    }
}
