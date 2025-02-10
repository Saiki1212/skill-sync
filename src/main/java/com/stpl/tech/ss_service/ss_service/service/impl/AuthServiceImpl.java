package com.stpl.tech.ss_service.ss_service.service.impl;

import com.stpl.tech.ss_service.ss_service.config.annotations.MasterTransactional;
import com.stpl.tech.ss_service.ss_service.config.jwtConfig.JWTService;
import com.stpl.tech.ss_service.ss_service.dbDomain.repository.UserBaseDetailRepo;
import com.stpl.tech.ss_service.ss_service.exception.FieldsNotValidException;
import com.stpl.tech.ss_service.ss_service.exception.LoginFailedException;
import com.stpl.tech.ss_service.ss_service.modal.dto.UserLoginRegisterDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.modal.enums.UserStatus;
import com.stpl.tech.ss_service.ss_service.resource.AppUtil;
import com.stpl.tech.ss_service.ss_service.service.AuthService;
import com.stpl.tech.ss_service.ss_service.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserBaseDetailRepo userRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @MasterTransactional
    public String loginUser(UserLoginRegisterDto userLoginRegisterDto) throws FieldsNotValidException {
        isRequiredFieldsPresent(userLoginRegisterDto);

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userLoginRegisterDto.getUsername(), userLoginRegisterDto.getPassword())
                );

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(userLoginRegisterDto.getUsername());
        }
        throw new LoginFailedException("User not found for username : " + userLoginRegisterDto.getUsername());
    }

    @Override
    @MasterTransactional
    public String registerUser(UserLoginRegisterDto details) throws FieldsNotValidException {
        isRequiredFieldsPresent( details);
        UserBaseDetailData user = userRepo.findByUsername(details.getUsername());
        if(Objects.nonNull(user)) {
            throw new RuntimeException("User already exists with this  username : " + details.getUsername());
        }
        saveUser(details);
        return jwtService.generateToken(details.getUsername());
    }

    private void isRequiredFieldsPresent(UserLoginRegisterDto details) throws FieldsNotValidException {

        if(AppUtil.hasNoLength(details.getUsername()) || AppUtil.hasNoLength(details.getPassword())) {
            throw new FieldsNotValidException("Required fields not found");
        }

    }

    private void saveUser(UserLoginRegisterDto details) {
        UserBaseDetailData user = new UserBaseDetailData();
        user.setUsername(details.getUsername());
        user.setPassword(passwordEncoder.encode(details.getPassword()));
        user.setUserStatus(UserStatus.ACTIVE);
        userRepo.save(user);
    }

}
