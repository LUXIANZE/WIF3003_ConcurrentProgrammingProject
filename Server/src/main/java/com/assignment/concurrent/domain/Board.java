package com.assignment.concurrent.domain;

import com.assignment.concurrent.service.StopTaskService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Timer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Board {
    private int m; //max execution time
    private Point[] points;
    private PointPairingTask[] pointPairingTasks;
    private Timer timer;
    private ExecutorService executorService;
    private long startTime;
    private StopTaskService stopTask;
    private List<Edge> edges;

    public Board(Point[] points, PointPairingTask[] pointPairingTasks, Timer timer, ExecutorService executorService, StopTaskService stopTask, int m) {
        this.points = points;
        this.pointPairingTasks = pointPairingTasks;
        this.timer = timer;
        this.executorService = executorService;
        this.stopTask = stopTask;
        this.m = m;
        edges = new ArrayList<>();
    }
    
    public void startProgram(){
        timer.schedule(stopTask, m * 1000);//to schedule the shutdown task so the program 
        //must end after m seconds
        this.startTime = System.currentTimeMillis();
        
        //return edgeLists from PointPairingTask and add them into edges
        try {
            List<Future<List>> futureList = executorService.invokeAll(Arrays.asList(pointPairingTasks));
            for (Future<List> list : futureList) {
                edges.addAll(list.get());
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Edge> getEdges(){
        return edges;
    }
}
