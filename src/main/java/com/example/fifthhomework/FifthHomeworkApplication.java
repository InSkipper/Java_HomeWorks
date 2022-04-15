package com.example.fifthhomework;

import com.example.fifthhomework.eventpublishers.EventPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FifthHomeworkApplication {
    private static EventPublisher publisher;

    public FifthHomeworkApplication(EventPublisher publisher) {
        FifthHomeworkApplication.publisher = publisher;
    }

    public static void main(String[] args) {
        SpringApplication.run(FifthHomeworkApplication.class, args);

        publisher.publishEvent("Event message!");
    }

}
