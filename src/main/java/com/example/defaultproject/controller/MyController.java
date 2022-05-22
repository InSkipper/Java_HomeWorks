package com.example.defaultproject.controller;

import com.example.defaultproject.annotation.WithApiMaxCount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/api")
    @WithApiMaxCount
    public String apiMethod() {
        return "API response";
    }
}
