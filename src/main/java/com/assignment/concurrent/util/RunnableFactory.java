package com.assignment.concurrent.util;

import com.assignment.concurrent.domain.Coordinate;
import com.assignment.concurrent.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Set;

@Component
public class RunnableFactory {

    @Autowired
    private MessageService messageService;

    public Runnable createRunnableSpammer(Set<Coordinate> points) {
        return new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    String threadName = Thread.currentThread().getName();
                    Coordinate coordinate = PointsUtil.pop(points);
                    messageService.send( threadName + " says Annyeong with coordinate " + coordinate);
                }
            }
        };
    }
}
