package com.example.fifthhomework.eventpublishers;

import com.example.fifthhomework.events.MyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    private final ApplicationEventPublisher publisher;

    public EventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    public void publishEvent() {
        publisher.publishEvent(new MyEvent("Event message!"));
    }
}