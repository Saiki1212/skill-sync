package com.stpl.tech.ss_service.ss_service.service.mapper;

import com.stpl.tech.ss_service.ss_service.modal.dto.UserLoginRegisterDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailMapperService {

    UserBaseDetailData convertUserLoginData(UserLoginRegisterDto userLoginRegisterDto);

}
