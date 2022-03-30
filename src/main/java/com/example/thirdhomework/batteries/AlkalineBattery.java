package com.example.thirdhomework.batteries;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Scope(value = "prototype")
public class AlkalineBattery implements Battery {
    public AlkalineBattery() {
    }

    @Override
    public int getChargeLevel() {
        return hashCode() % 100;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("You took the alkaline battery out of you pocket. Code: " + hashCode());
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("You shoved the alkaline battery in your pocket. Code: " + hashCode());
    }
}
