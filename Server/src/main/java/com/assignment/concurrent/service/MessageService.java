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

    public synchronized void send(String message) {
        try {
            Thread.sleep(1000);
            Set<Point> aPairOfPoints = new HashSet();
            aPairOfPoints.add(new Point(randomDouble(), 2.2));
            aPairOfPoints.add(new Point(randomDouble(), 4.2));
            messagingTemplate.convertAndSend( "/topic/edge", new Edge(aPairOfPoints) );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Double randomDouble() {
        Double value = new Random().nextDouble()*1000;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(value));
    }
}