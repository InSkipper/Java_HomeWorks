package com.example.thirdhomework.electronics;

import com.example.thirdhomework.batteries.Battery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Flashlight {
    private final Battery alkalineBattery;

    public void doWork() {
        System.out.println("A beam of light illuminated the room.");
        System.out.println("The built-in screen showed " + alkalineBattery.getChargeLevel() + "%");
        System.out.println("Battery hashcode: " + alkalineBattery.hashCode());
    }
}
