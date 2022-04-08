package com.example.fifthhomework.listeners;

import com.example.fifthhomework.events.MyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CommonEventListener {
    @EventListener
    public void listenEvent(MyEvent event) {
        System.out.println("Common event listener: " + event.getMessage()
                + " In thread: " + Thread.currentThread().getName());
    }
}
