package com.stpl.tech.ss_service.ss_service.utilService.mapper.impl;

import com.stpl.tech.ss_service.ss_service.modal.dto.UserLoginRegisterDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.modal.enums.UserStatus;
import com.stpl.tech.ss_service.ss_service.utilService.mapper.UserDetailMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailMapperServiceImpl implements UserDetailMapperService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserBaseDetailData convertUserLoginData(UserLoginRegisterDto registerData) {
        UserBaseDetailData userData = new UserBaseDetailData();
        userData.setUsername(registerData.getUsername());
        userData.setPassword(passwordEncoder.encode(registerData.getPassword()));
        userData.setUserStatus(UserStatus.ACTIVE);
        userData.setFullName(registerData.getFullName());
        userData.setEmail(registerData.getEmail());
        userData.setPhoneNumber(registerData.getPhoneNumber());

        return userData;
    }

}
