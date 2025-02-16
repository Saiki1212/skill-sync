package com.stpl.tech.ss_service.ss_service.config.annotations;


import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNullCustomValidator.class)
@Documented
public @interface CustomNotNull {

    String value() default "";

    String message() default "";

}
