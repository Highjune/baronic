package com.hospital.baronic.domain.user;

import com.hospital.baronic.web.dto.ReservationResponseDto;
import com.hospital.baronic.web.dto.ReservationSaveRequestDto;
import com.hospital.baronic.web.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void registerUser(User user) throws Exception;

    UserDto isAlreadyID(String id) throws Exception;

    String get_SALT(String id) throws Exception;
}
