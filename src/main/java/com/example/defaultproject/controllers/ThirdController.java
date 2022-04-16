package com.example.defaultproject.controllers;

import com.example.defaultproject.responses.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdController {
    @GetMapping("/exception")
    public Response testException(@RequestParam(required = false, defaultValue = "false") boolean exception) throws Exception {
        if (exception) {
            throw new Exception();
        }
        return new Response("Ok");
    }
}
