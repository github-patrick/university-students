package com.university.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = StudentDoesNotExistException.class)
    protected ResponseEntity<Object> handleStudentDoesNotExistException(RuntimeException ex, WebRequest request) {

        CustomApiErrorResponse customApiErrorResponse = new CustomApiErrorResponse();
        customApiErrorResponse.setTimeStamp(LocalDateTime.now());
        customApiErrorResponse.setError(ex.getMessage());
        customApiErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(customApiErrorResponse, HttpStatus.NOT_FOUND);


    }
}
