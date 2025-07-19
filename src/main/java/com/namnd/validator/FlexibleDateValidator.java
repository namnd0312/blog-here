package com.namnd.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class FlexibleDateValidator implements ConstraintValidator<ValidDateFormat, String> {

    private String pattern;

    @Override
    public void initialize(ValidDateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true; // Cho phép bỏ trống
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                    .withResolverStyle(ResolverStyle.STRICT); // tránh nhầm lẫn format sai
            LocalDateTime.parse(value, formatter);
            return true;
        } catch (DateTimeParseException e1) {
            try {
                // Nếu không parse được LocalDateTime, thử với LocalDate (nếu format không có time)
                LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT));
                return true;
            } catch (DateTimeParseException e2) {
                return false;
            }
        }
    }
}
