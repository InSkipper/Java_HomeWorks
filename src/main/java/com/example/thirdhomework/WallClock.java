package com.example.thirdhomework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class WallClock {
    @Autowired
    @Qualifier("lithiumIonBattery")
    private Battery battery;

    public void doWork() {
        System.out.println("The clock showed: 'Current time: perfect for lunch'");
        System.out.println("The built-in screen showed " + battery.getChargeLevel() + "%");
        System.out.println("Battery hashcode: " + battery.hashCode());
    }
}
