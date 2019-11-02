package com.university.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = StudentDoesNotExistException.class)
    protected ResponseEntity<Object> handleStudentDoesNotExistException(RuntimeException ex, WebRequest request) {

        CustomApiErrorResponse customApiErrorResponse = new CustomApiErrorResponse();
        customApiErrorResponse.setTimeStamp(LocalDateTime.now());
        customApiErrorResponse.setError(ex.getMessage());
        customApiErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(customApiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomApiErrorResponse customApiErrorResponse = new CustomApiErrorResponse();
        customApiErrorResponse.setTimeStamp(LocalDateTime.now());
        customApiErrorResponse.setError(ex.getBindingResult().getFieldError().getDefaultMessage());
        customApiErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(customApiErrorResponse, HttpStatus.BAD_REQUEST);
    }




}
