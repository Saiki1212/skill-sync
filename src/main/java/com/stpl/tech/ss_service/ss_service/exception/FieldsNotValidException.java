package com.stpl.tech.ss_service.ss_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "some fields was null")
public class FieldsNotValidException extends Exception {

    public FieldsNotValidException(String message) {
        super(message);
    }

}
