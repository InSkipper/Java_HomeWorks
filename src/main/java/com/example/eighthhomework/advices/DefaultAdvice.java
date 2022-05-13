package com.example.eighthhomework.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldError> handleValidationException(MethodArgumentNotValidException e) {
//        var response = new Response("400 Bed Request");
        return new ResponseEntity<>(e.getFieldError(), HttpStatus.BAD_REQUEST);
    }
}
