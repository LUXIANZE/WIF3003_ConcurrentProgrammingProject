package com.assignment.concurrent.service;

import com.assignment.concurrent.domain.Coordinate;
import com.assignment.concurrent.util.PointsUtil;
import com.assignment.concurrent.util.RunnableFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ConqueringService {

    private final RunnableFactory runnableFactory;

    public ConqueringService(RunnableFactory runnableFactory, PointsUtil pointsFactory) {
        this.runnableFactory = runnableFactory;
    }

    public void start() {
        Set<Coordinate> points = PointsUtil.createPoints(100);
        //can change to Thread pool
        Thread thread1 = new Thread(runnableFactory.createRunnableSpammer(points));
        Thread thread2 = new Thread(runnableFactory.createRunnableSpammer(points));
        thread1.start();
        thread2.start();
    }
}