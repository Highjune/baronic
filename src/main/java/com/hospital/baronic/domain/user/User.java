package com.hospital.baronic.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class User {
    int id;
    String passwd;
    String salt;
    int is_valid;
}
