package com.example.fifthhomework.eventpublishers;

import com.example.fifthhomework.events.MyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventPublisher {
    private final ApplicationEventPublisher publisher;

    public EventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Transactional
    public void publishEvent(String message) {
        publisher.publishEvent(new MyEvent(message));
    }
}