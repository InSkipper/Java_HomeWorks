package com.example.defaultproject.controllers;

import com.example.defaultproject.dto.MyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SecondController {
    @GetMapping("/json")
    public ResponseEntity<MyDTO> readJSON(@RequestBody MyDTO input) {
        return new ResponseEntity<>(input.setId(123), HttpStatus.OK);
    }
}

