package com.assignment.concurrent.service;

import com.assignment.concurrent.domain.Edge;
import com.assignment.concurrent.domain.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class MessageService {

    @Autowired
    public SimpMessageSendingOperations messagingTemplate;

    public synchronized void send(String channelName, Object data) {
        String destination = "/topic/" + channelName;
        messagingTemplate.convertAndSend(destination, data);
    }

}