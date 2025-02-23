package com.stpl.tech.ss_service.ss_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({LoginFailedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorTemplate handleLoginFailedException(HttpServletRequest request, LoginFailedException exp)  {
        ErrorTemplate error = new ErrorTemplate();
        error.setExceptionType(HttpStatus.UNAUTHORIZED.name());
        error.setErrorTitle("Login Failed");
        error.setErrorMsg(exp.getMessage());
        error.setPath(request.getRequestURI());
        return error;
    }

    @ResponseBody
    @ExceptionHandler(FieldsNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorTemplate handleValidationExceptions(HttpServletRequest request, FieldsNotValidException exp) {
        ErrorTemplate error = new ErrorTemplate();
        error.setExceptionType(HttpStatus.NOT_FOUND.name());
        error.setErrorTitle("Mandatory fields are required");
        error.setErrorMsg(exp.getMessage());
        error.setPath(request.getRequestURI());
        return error;
    }


    @ResponseBody
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorTemplate handleAllExceptions(HttpServletRequest request, Exception exp) {
        return returnCompleteErrorMessage(exp, "An Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorTemplate handleConstraintViolation(HttpServletRequest request, ConstraintViolationException e) {
        return returnCompleteErrorMessage(e, "Not null fields are null", HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(CreatorException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorTemplate handleCreatorExceptions(HttpServletRequest request, CreatorException exp) {
        return returnCompleteErrorMessage(exp, "Error in creator", HttpStatus.FORBIDDEN, request);
    }

    private ErrorTemplate returnCompleteErrorMessage(Exception exp, String ErrorMessage, HttpStatus httpStatus, HttpServletRequest request) {
        StackTraceElement[] stackTrace = exp.getStackTrace();
        ErrorTemplate errorTemplate = new ErrorTemplate();

        if (stackTrace != null && stackTrace.length > 0) {
            errorTemplate.setErrorClassName(stackTrace[0].getClassName());
            errorTemplate.setErrorMethodName(stackTrace[0].getMethodName());
            errorTemplate.setErrorLineNumber(stackTrace[0].getLineNumber());
        }

        errorTemplate.setTimestamp(LocalDateTime.now());
        errorTemplate.setErrorTitle(ErrorMessage);
        errorTemplate.setErrorMsg(exp.getMessage());
        errorTemplate.setPath(request.getRequestURI());
        errorTemplate.setExceptionType(httpStatus.name());

        log.error("Exception occurred in Class: {}, Method: {}, Line: {}, Message: {}" ,
                errorTemplate.getErrorClassName() ,
                errorTemplate.getErrorMethodName() ,
                errorTemplate.getErrorLineNumber() ,
                exp.getMessage() , exp);

        return errorTemplate;
    }

}
