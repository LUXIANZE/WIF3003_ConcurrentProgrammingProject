package com.assignment.concurrent.util;

import com.assignment.concurrent.domain.Point;
import com.assignment.concurrent.service.MessageService;
import com.assignment.concurrent.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RunnableFactory {

    @Autowired
    private MessageService messageService;

    public Runnable createRunnableSpammer(Set<Point> points) {
        return new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    String threadName = Thread.currentThread().getName();
                    // Point point = PointsService.pop(points);
                    // messageService.send( threadName + " says Annyeong with coordinate " + point);
                }
            }
        };
    }
}
