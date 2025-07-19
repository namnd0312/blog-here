package com.namnd.config;

import com.namnd.dto.response.ResponseDTO;
import com.namnd.enums.MessageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@ControllerAdvice
@Component
public class ExceptionCustomHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(ExceptionCustomHandler.class);

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ResponseDTO<?>> sqlExceptionHandler(SQLException sqlException) {
        LOGGER.error("SQL_EXCEPTION ==============>> {}", sqlException.getMessage());
        sqlException.printStackTrace();
        return ResponseEntity.ok(ResponseDTO.error(MessageEnum.SQL_EXCEPTION.getCode(), MessageEnum.SQL_EXCEPTION.formatMessage(sqlException.getMessage())));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDTO<?>> sqlViolationExceptionHandler(SQLException sqlException) {
        LOGGER.error("SQL_EXCEPTION ==============>> {}", sqlException.getMessage());
        sqlException.printStackTrace();
        return ResponseEntity.ok(ResponseDTO.error(MessageEnum.SQL_EXCEPTION.getCode(), MessageEnum.SQL_EXCEPTION.formatMessage(sqlException.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<?>> exceptionHandler(Exception ex) {
        LOGGER.error("UNHANDLED_ERROR ==============>> {}", ex.getMessage());
        ex.getStackTrace();
        return ResponseEntity.ok(ResponseDTO.error(MessageEnum.UN_HANDLE_ERROR.getCode(), MessageEnum.UN_HANDLE_ERROR.formatMessage(ex.getMessage())));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errors.put(e.getField(), e.getField() + " " + e.getDefaultMessage()));

        String description = String.join("#", new ArrayList<>(errors.values()));

        return ResponseEntity.ok(ResponseDTO.error(MessageEnum.FIELD_INVALID.getCode(), MessageEnum.FIELD_INVALID.formatMessage(description)));
    }

}
