package com.assignment.concurrent.service;

import com.assignment.concurrent.domain.Board;
import com.assignment.concurrent.domain.Point;
import com.assignment.concurrent.domain.UserInputMessage;
import com.assignment.concurrent.util.RunnableFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ConquerService {

    private final RunnableFactory runnableFactory;

    public ConquerService(RunnableFactory runnableFactory, PointsService pointsFactory) {
        this.runnableFactory = runnableFactory;
    }

    public Board start(UserInputMessage userInputMessage) {
        Set<Point> points = PointsService.createPoints(userInputMessage.getN());
        Board board = new Board();
        board.setPoints(points);
        //can change to Thread pool
        Thread thread1 = new Thread(runnableFactory.createRunnableSpammer(points));
        Thread thread2 = new Thread(runnableFactory.createRunnableSpammer(points));
        thread1.start();
        thread2.start();
        return board;
    }
}