package com.example.defaultproject.controlleradvises;

import com.example.defaultproject.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvise {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e) {
        var response = new Response("502 Bad Gateway");
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }
}
