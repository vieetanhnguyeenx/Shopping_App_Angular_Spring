package com.project.shopapp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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

    @ExceptionHandler(value = {SizeLimitExceededException.class})
    @ResponseBody
    public ResponseEntity<Object> handleSizeLimitExceededException(
            SizeLimitExceededException sizeLimitExceededException,
            HttpServletRequest request) {

        ApiRequestException apiRequestException = ApiRequestException
                .exception(List.of(sizeLimitExceededException.getMessage().toString()),
                        HttpStatus.PAYLOAD_TOO_LARGE);
        apiRequestException.getException().setPath(request.getRequestURI().toString());

        return new ResponseEntity<>(apiRequestException.getException(), apiRequestException.getHttpStatus());
    }
}
