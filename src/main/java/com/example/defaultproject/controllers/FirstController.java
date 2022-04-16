package com.example.defaultproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FirstController {
    @GetMapping("/headers")
    public String readHeaders(@RequestHeader Map<String, String> headers) {
        var sb = new StringBuilder();
        headers.forEach((key, value) -> {
            sb.append(key).append("\n");
        });

        return sb.toString();
    }
}
