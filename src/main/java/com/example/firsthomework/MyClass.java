package com.example.firsthomework;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class MyClass {
    @Getter
    private final String text = "Hello from bean!";
}
