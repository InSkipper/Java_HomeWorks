package com.example.fifthhomework.listeners;

import com.example.fifthhomework.events.MyEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class TransactEventListener {

    //ПРОБЛЕМА: Работает точно так же, как и EventListener
    @TransactionalEventListener
    public void listenEvent(MyEvent event) {
        System.out.println("Transactional event listener: " + event.getMessage()
                + " In thread: " + Thread.currentThread().getName());
    }
}
