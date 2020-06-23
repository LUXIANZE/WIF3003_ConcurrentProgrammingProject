package com.assignment.concurrent.domain;

import com.assignment.concurrent.service.MessageService;
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
    private long endTime;
    private StopTaskService stopTask;
    private final MessageService messageService;
    private List<Edge> edges;

    public Board(Point[] points, PointPairingTask[] pointPairingTasks, Timer timer, ExecutorService executorService, StopTaskService stopTask, int m, MessageService messageService) {
        this.points = points;
        this.pointPairingTasks = pointPairingTasks;
        this.timer = timer;
        this.executorService = executorService;
        this.messageService = messageService;
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
            boolean programEnd = false;
            List<Future<List>> futureList = executorService.invokeAll(Arrays.asList(pointPairingTasks));
            long tempEndTime = System.currentTimeMillis();
            
            //this for loop will be running after all executorService task return
            //this for loop to check whether the program end because of 20 fail attempts
            //if it end because of 20 fail attempts the Stop Task that was scheduled
            //at the start should be cancelled
            //the executorService will be shutdown at here if 20 fail attempts reached
            //otherwise Stop Task Service should shut down it already
            boolean endByStopTask = true;
            for (int i = 0; i < pointPairingTasks.length; i++) {
                if (pointPairingTasks[i].getFailAttempts() >= 20) {
                    endByStopTask = false;
                    this.endTime = tempEndTime;
                    timer.cancel();
                    executorService.shutdownNow();
                    break;
                }
            }
            if(endByStopTask){
                this.endTime = stopTask.getEndTime();
            }
            
            for (Future<List> list : futureList) {
                edges.addAll(list.get());
                // messageService.send("edge", edges);
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
    
    public Point[] getPoints(){
        return points;
    }
    
    public long getEndTime(){
        return this.endTime;
    }
    
    public long getStartTime(){
        return this.startTime;
    }
}
