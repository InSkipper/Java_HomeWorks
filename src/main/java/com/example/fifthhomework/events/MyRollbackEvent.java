package com.example.fifthhomework.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyRollbackEvent {
    private final String message;
}
