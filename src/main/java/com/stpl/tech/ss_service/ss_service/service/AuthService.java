package com.stpl.tech.ss_service.ss_service.service;

import com.stpl.tech.ss_service.ss_service.exception.FieldsNotValidException;
import com.stpl.tech.ss_service.ss_service.modal.dto.UserLoginRegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public String loginUser(UserLoginRegisterDto userLoginRegisterDto) throws FieldsNotValidException;

    public String registerUser(UserLoginRegisterDto userLoginRegisterDto) throws Exception;

}
