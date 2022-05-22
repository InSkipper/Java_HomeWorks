package com.example.defaultproject;

import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Produser {

    private JmsTemplate template;

    public void sendMessage(String text) {
        template.send("inbound.queue", session -> session.createTextMessage(text));
    }
}
