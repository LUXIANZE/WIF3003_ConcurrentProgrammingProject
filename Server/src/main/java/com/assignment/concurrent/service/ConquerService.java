package com.assignment.concurrent.service;

import com.assignment.concurrent.domain.Board;
import com.assignment.concurrent.domain.Point;
import com.assignment.concurrent.domain.PointPairingTask;
import com.assignment.concurrent.domain.UserInputMessage;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ConquerService {

    private final PointsService pointsService;
    private final MessageService messageService;

    public ConquerService(PointsService pointsService, MessageService messageService) {
        this.pointsService = pointsService;
        this.messageService = messageService;
    }

    public Board start(UserInputMessage userInputMessage) {
        int n = userInputMessage.getN();
        int m = userInputMessage.getM();
        int t = userInputMessage.getT();
        Timer timer = new Timer();
        ExecutorService executorService = Executors.newFixedThreadPool(t);
        StopTaskService stopTask = new StopTaskService(executorService, timer);
        Set<Point> points = pointsService.generatePoints(n);
        Point[] pointsArr = new Point[n];
        points.toArray(pointsArr);
        messageService.send("points", pointsArr);
        PointPairingTask[] pointPairingTasks = new PointPairingTask[t];
        for (int i = 0; i < t; i++) {
            pointPairingTasks[i] = new PointPairingTask(pointsArr, messageService);
        }
        Board board = new Board(pointsArr, pointPairingTasks, timer, executorService
                , stopTask, m);
//        Callable C = new PointPairingTask(pointsArr, messageService);
        board.startProgram();
        return board;
    }
}