package com.project.shopapp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    @ResponseBody
    public ResponseEntity<Object> handleInvalidRequestException(
            ApiRequestException apiRequestException,
            HttpServletRequest request) {

        apiRequestException.getException().setPath(request.getRequestURI().toString());
        return new ResponseEntity<>(apiRequestException.getException(), apiRequestException.getHttpStatus());
    }
}
