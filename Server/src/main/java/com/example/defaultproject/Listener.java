package com.example.defaultproject;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Listener {
    @JmsListener(destination = "inbound.queue")
    public void receiveMessage(TextMessage jsonMessage) throws JMSException {
        System.out.println("Получено сообщение: " + jsonMessage.getText());
    }
}
