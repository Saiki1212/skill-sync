package com.stpl.tech.ss_service.ss_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorTemplate {
    private String errorClassName;
    private String errorMethodName;
    private int errorLineNumber;
    private String errorTitle;
    private String errorMsg;
    private String exceptionType;
    private String path;
    private LocalDateTime timestamp;
}
