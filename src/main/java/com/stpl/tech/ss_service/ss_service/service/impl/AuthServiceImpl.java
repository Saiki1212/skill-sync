package com.stpl.tech.ss_service.ss_service.service.impl;

import com.stpl.tech.ss_service.ss_service.config.annotations.MasterTransactional;
import com.stpl.tech.ss_service.ss_service.config.jwtConfig.JWTService;
import com.stpl.tech.ss_service.ss_service.dbDomain.repository.UserBaseDetailRepo;
import com.stpl.tech.ss_service.ss_service.exception.FieldsNotValidException;
import com.stpl.tech.ss_service.ss_service.exception.LoginFailedException;
import com.stpl.tech.ss_service.ss_service.modal.dto.UserLoginRegisterDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.resource.AppConstants;
import com.stpl.tech.ss_service.ss_service.service.AuthService;
import com.stpl.tech.ss_service.ss_service.service.email.EmailService;
import com.stpl.tech.ss_service.ss_service.service.email.RegistrationSuccessEMail;
import com.stpl.tech.ss_service.ss_service.service.mapper.UserDetailMapperService;
import io.micrometer.common.util.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserBaseDetailRepo userRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserDetailMapperService userDetailMapperService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

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
    public String registerUser(UserLoginRegisterDto details) throws Exception {
        isRequiredFieldsPresent( details);
        UserBaseDetailData user = userRepo.findByUsername(details.getUsername());
        if(Objects.nonNull(user)) {
            throw new RuntimeException("User already exists with this  username : " + details.getUsername());
        }
        UserBaseDetailData userData = userDetailMapperService.convertUserLoginData(details);
        userData = userRepo.save(userData);

        scheduleUserEmailForRegistration(userData, 1);

        return jwtService.generateToken(details.getUsername());
    }

    private void isRequiredFieldsPresent(UserLoginRegisterDto details) throws FieldsNotValidException {

        if(StringUtils.isBlank(details.getUsername()) || StringUtils.isBlank(details.getPassword())) {
            throw new FieldsNotValidException("Required fields not found");
        }

    }

    private void scheduleUserEmailForRegistration(UserBaseDetailData userData, int ignoredDelayInMinutes) {
        taskScheduler.schedule(
                () ->  sendEmailForSuccessfulRegistration(userData),
                Instant.now().plusSeconds(ignoredDelayInMinutes * 30)
        );
        log.info("Email scheduled for: {} in {} minutes.", userData.getUsername(), ignoredDelayInMinutes);
    }

    @Async
    @SneakyThrows
    private void sendEmailForSuccessfulRegistration(UserBaseDetailData userData) {

        RegistrationSuccessEMail email = new RegistrationSuccessEMail(
                userData,
                AppConstants.SEND_EMAIL_FROM
        );

        emailService.sendEmail(email);
    }

}
