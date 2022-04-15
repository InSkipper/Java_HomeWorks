package com.example.fifthhomework.listeners;

import com.example.fifthhomework.events.MyEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class TransactEventListener {

    //ПРОБЛЕМА: Работает точно так же, как и EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void listenEvent(MyEvent event) {
        System.out.println("First Transactional event listener: " + event.getMessage()
                + " In thread: " + Thread.currentThread().getName());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void listenAnotherEvent(MyEvent event) {
        System.out.println("Second Transactional event listener: " + event.getMessage()
                + " In thread: " + Thread.currentThread().getName());
    }
}