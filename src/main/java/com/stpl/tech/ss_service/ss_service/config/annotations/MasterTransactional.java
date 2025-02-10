package com.stpl.tech.ss_service.ss_service.config.annotations;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
public @interface MasterTransactional {

    String value() default "";

    boolean readOnly() default false;

    Propagation propagation() default Propagation.REQUIRED;

    Class<? extends Throwable>[] rollbackFor() default {Exception.class};

}
