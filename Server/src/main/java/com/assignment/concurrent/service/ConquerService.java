package com.assignment.concurrent.service;

import com.assignment.concurrent.domain.Board;
import com.assignment.concurrent.domain.Point;
import com.assignment.concurrent.domain.PointPairingTask;
import com.assignment.concurrent.domain.UserInputMessage;
import com.assignment.concurrent.util.RunnableFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.Callable;

@Service
public class ConquerService {

    private final RunnableFactory runnableFactory;

    public ConquerService(RunnableFactory runnableFactory, PointsService pointsFactory) {
        this.runnableFactory = runnableFactory;
    }

    public Board start(UserInputMessage userInputMessage) {
        int n = userInputMessage.getN();
        int m = userInputMessage.getM();
        int t = userInputMessage.getT();
        Board board = new Board(n, m, t);
        Set<Point> points = PointsService.generatePoints(n);
        Point[] pointsArr = new Point[n];
        points.toArray(pointsArr);
        Callable C = new PointPairingTask(pointsArr);
        // board.setPoints(points);
        //can change to Thread pool
        Thread thread1 = new Thread(runnableFactory.createRunnableSpammer(points));
        Thread thread2 = new Thread(runnableFactory.createRunnableSpammer(points));
        thread1.start();
        thread2.start();
        return board;
    }
}