package com.stpl.tech.ss_service.ss_service.resource.controller;

import com.stpl.tech.ss_service.ss_service.exception.FieldsNotValidException;
import com.stpl.tech.ss_service.ss_service.modal.dto.UserLoginRegisterDto;
import com.stpl.tech.ss_service.ss_service.resource.SSResourceUtil;
import com.stpl.tech.ss_service.ss_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SSResourceUtil.BASE_PATH + SSResourceUtil.AUTH_ROOT_CONTEXT)
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public String loginUser(@RequestBody UserLoginRegisterDto loginDetails) throws FieldsNotValidException {
        return authService.loginUser(loginDetails);
    }

    @PostMapping("register")
    public String registerUser(@RequestBody UserLoginRegisterDto registerDetails) throws Exception {
        return authService.registerUser(registerDetails);
    }

}
