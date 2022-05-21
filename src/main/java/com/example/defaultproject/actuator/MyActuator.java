package com.example.defaultproject.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Endpoint(id = "custom")
@Slf4j
public class MyActuator {

    @ReadOperation
    public String getStatus() {
        var dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm:ss");
        var now = LocalDateTime.now();
        return "Текущая дата и время: " + now.format(dtf);
    }
}
