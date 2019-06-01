package com.bettercode.connect.web.rest;


import com.bettercode.connect.excel.exception.IllegalExcelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value = {IllegalExcelException.class})
    public final ResponseEntity<Map<String, String>> handleIllegalExcelException(IllegalExcelException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }
}
