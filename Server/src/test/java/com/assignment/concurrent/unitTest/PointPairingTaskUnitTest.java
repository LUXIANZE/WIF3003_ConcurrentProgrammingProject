package com.assignment.concurrent.unitTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.assignment.concurrent.domain.Point;
import com.assignment.concurrent.domain.PointPairingTask;

import com.assignment.concurrent.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PointPairingTaskUnitTest {
    Point[] arr = generateTestCases();
    @Autowired
    private MessageService messageService;
    Callable PPT = new PointPairingTask(arr, messageService);

    @Test
    public void TestCallMethod(){
        ExecutorService ES = Executors.newFixedThreadPool(1);
        ES.submit(PPT);
        ES.shutdown();
    }

    private Point[] generateTestCases(){
        Point[] arr = new Point[]{
            new Point(1, 1),
            new Point(2, 2),
            new Point(3, 3),
            new Point(4, 4),
        };
        
        return arr;
    }

}