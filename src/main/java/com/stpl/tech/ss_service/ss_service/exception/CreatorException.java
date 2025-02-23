package com.stpl.tech.ss_service.ss_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "exception related to creator")
public class CreatorException extends RuntimeException {

    private final String errorTitle;

    public CreatorException(String message, String errorTitle) {
        super(message);
        this.errorTitle = errorTitle;
    }

}
