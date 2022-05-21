package com.example.defaultproject.controller;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    Counter counter;

    @Autowired
    public MyController(MeterRegistry registry) {
        counter = registry.counter("api.usage");
    }

    @GetMapping("/first")
    @Timed(value = "firstMethod.time", description = "Time needed to do first method")
    public String firstMethod() {
        counter.increment();
        return "First method";
    }

    @GetMapping("/second")
    public String secondMethod() {
        counter.increment();
        return "Second method";
    }
}
