package com.stpl.tech.ss_service.ss_service.config.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotNullCustomValidator implements ConstraintValidator<CustomNotNull, Object> {

    private String fieldName;
    private String customMessage;

    @Override
    public void initialize(CustomNotNull constraintAnnotation) {
        this.fieldName = constraintAnnotation.value();
        this.customMessage = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        String defaultMsg = "Required field was null or empty; it should not be null or empty";

        if (!fieldName.isEmpty()) {
            defaultMsg = fieldName + " : " + defaultMsg;
        }

        if (!customMessage.isEmpty()) {
            defaultMsg = defaultMsg + " \n" + customMessage;
        }

        if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(defaultMsg).addConstraintViolation();
            return false;
        }

        return true;
    }

}
