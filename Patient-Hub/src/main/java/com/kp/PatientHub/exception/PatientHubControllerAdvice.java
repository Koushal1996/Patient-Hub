package com.kp.PatientHub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class PatientHubControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException ex, WebRequest request){
        ErrorMessage errorMessage= ErrorMessage.builder().message(ex.getMessage()).statusCode(HttpStatus.NOT_FOUND.value()).build();
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorMessage> validationException(ValidationException ex, WebRequest request){
        ErrorMessage errorMessage= ErrorMessage.builder().message(ex.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
}
