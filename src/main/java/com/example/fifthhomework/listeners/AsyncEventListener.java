package com.example.fifthhomework.listeners;

import com.example.fifthhomework.events.MyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncEventListener {
    @Async("threadPoolTaskExecutor")
    @EventListener
    public void listenEvent(MyEvent event) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Async event listener: " + event.getMessage()
                + " In thread: " + Thread.currentThread().getName());
    }
}
