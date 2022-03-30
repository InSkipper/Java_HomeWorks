package com.example.thirdhomework.batteries;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class LithiumIonBattery implements Battery {
    public LithiumIonBattery() {
    }

    @Override
    public int getChargeLevel() {
        return hashCode() % 100;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("You took the lithium-ion battery out of you pocket. Code: " + hashCode());
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("You shoved the lithium-ion battery in your pocket. Code: " + hashCode());
    }
}
