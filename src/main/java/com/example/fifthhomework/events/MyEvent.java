package com.example.fifthhomework.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyEvent {
    @Getter
    private final String message;
}
