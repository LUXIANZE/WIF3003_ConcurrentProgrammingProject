package com.assignment.concurrent.service;

import com.assignment.concurrent.domain.Board;
import com.assignment.concurrent.domain.Point;
import com.assignment.concurrent.domain.PointPairingTask;
import com.assignment.concurrent.domain.UserInputMessage;
import com.assignment.concurrent.util.RunnableFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ConquerService {

    private final RunnableFactory runnableFactory;
    private final MessageService messageService;

    public ConquerService(RunnableFactory runnableFactory, PointsService pointsFactory) {
        this.runnableFactory = runnableFactory;
        this.messageService = messageService;
    }

    public Board start(UserInputMessage userInputMessage) {
        int n = userInputMessage.getN();
        int m = userInputMessage.getM();
        int t = userInputMessage.getT();
        Timer timer = new Timer();
        ExecutorService executorService = Executors.newFixedThreadPool(t);
        StopTaskService stopTask = new StopTaskService(executorService, timer);
        Set<Point> points = PointsService.generatePoints(n);
        Point[] pointsArr = new Point[n];
        points.toArray(pointsArr);
        PointPairingTask[] pointPairingTasks = new PointPairingTask[t];
        for (int i = 0; i < t; i++) {
            pointPairingTasks[i] = new PointPairingTask(pointsArr);
        }
        Board board = new Board(pointsArr, pointPairingTasks, timer, executorService
                , stopTask, m);
        Callable C = new PointPairingTask(pointsArr, messageService);
        // board.setPoints(points);
        //can change to Thread pool
//        Thread thread1 = new Thread(runnableFactory.createRunnableSpammer(points));
//        Thread thread2 = new Thread(runnableFactory.createRunnableSpammer(points));
//        thread1.start();
//        thread2.start();
        return board;
    }
}