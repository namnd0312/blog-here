package com.namnd.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FlexibleDateValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateFormat {

    String message() default "Invalid date format";

    /**
     * Định dạng cần kiểm tra, ví dụ: "dd/MM/yyyy" hoặc "dd/MM/yyyy HH:mm:ss"
     */
    String pattern();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
