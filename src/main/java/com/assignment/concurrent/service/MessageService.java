package com.assignment.concurrent.service;

import com.assignment.concurrent.domain.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    public SimpMessageSendingOperations messagingTemplate;

    public synchronized void send(String message) {
        try {
            Thread.sleep(1000);
            messagingTemplate.convertAndSend( "/topic/greetings", new Greeting(message) );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}