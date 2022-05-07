package com.example.fifthhomework.eventpublishers;

import com.example.fifthhomework.events.MyEvent;
import com.example.fifthhomework.events.MyRollbackEvent;
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
    public void publishEvent(MyEvent event) {
        System.out.println("___Publishing MyEvent___");
        publisher.publishEvent(event);
    }

    @Transactional
    public void publishEvent(MyRollbackEvent event) {
        System.out.println("___Publishing MyRollbackEvent___");
        publisher.publishEvent(event);
        throw new RuntimeException();
    }
}