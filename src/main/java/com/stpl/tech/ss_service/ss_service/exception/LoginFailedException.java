package com.stpl.tech.ss_service.ss_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "user not found!")
public class LoginFailedException extends RuntimeException {

    public LoginFailedException(String message) {
        super(message);
    }

}
