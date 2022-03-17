package com.example.thirdhomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThirdHomeworkApplication {
    public static Flashlight flashlight;
    public static MusicPlayer player;
    public static WallClock clock;

    public ThirdHomeworkApplication(Flashlight flashlight, MusicPlayer player, WallClock clock) {
        ThirdHomeworkApplication.flashlight = flashlight;
        ThirdHomeworkApplication.player = player;
        ThirdHomeworkApplication.clock = clock;
    }

    public static void main(String[] args) {
        SpringApplication.run(ThirdHomeworkApplication.class, args);

        flashlight.doWork();
        System.out.println();
        player.doWork();
        System.out.println();
        clock.doWork();
        System.out.println();
    }

}
