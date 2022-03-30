package com.example.thirdhomework.electronics;

import com.example.thirdhomework.batteries.Battery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MusicPlayer {
    private Battery battery;

    @Autowired
    public void serBattery(@Qualifier("alkalineBattery") Battery battery) {
        this.battery = battery;
    }

    public void doWork() {
        System.out.println("*Click*");
        System.out.println("You hear a quiet noise coming from player.");
        System.out.println("The built-in screen showed " + battery.getChargeLevel() + "%");
        System.out.println("Battery hashcode: " + battery.hashCode());
    }
}
