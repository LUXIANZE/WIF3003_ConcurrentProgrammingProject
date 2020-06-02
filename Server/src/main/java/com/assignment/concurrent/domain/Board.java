package com.assignment.concurrent.domain;

import com.assignment.concurrent.service.PointsService;
import java.util.HashSet;
import java.util.Random;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Board {
    private int n; //number of points
    private int t; //number of threads
    private int m; //max execution time
    private Set<Point> pointSet;
    private PointPairingTask[] threadArray;
    private Random rand;
    private Timer timer;
    private ExecutorService executorService;
    private long startTime;
    
    class StopTask extends TimerTask{
        @Override
        public void run() {
            long endTime = System.currentTimeMillis();
            System.out.println("Start:" + startTime);
            System.out.println("end  :" + endTime);
            System.out.println("end after " + (endTime - startTime));
            printEdgesFormed();
            PointPairingTask.killThread();//because the InterruptedException is catched,
            //need to set the variable to stop the Runnable
            executorService.shutdownNow();
            timer.cancel();//need to cancel the timer or the program wont end
        }
    }

    public Board(int n, int t, int m) {
        this.n = n;
        this.t = t;
        this.m = m;
        rand = new Random();
        this.pointSet = PointsService.generatePoints(n);
        createThread();
        executorService = Executors.newFixedThreadPool(t);
        timer = new Timer();
    }
    
    /**
     * point array is made as temporary object to pass into PointPairingTask class
     */
    private void createThread(){
        threadArray = new PointPairingTask[t];
        Point[] pointArray = pointSet.toArray(new Point[n]);
        for (int i = 0; i < t; i++) {
            threadArray[i] = new PointPairingTask(pointArray);
        }
    }
    
    public void startProgram(){
        StopTask task = new StopTask();
        timer.schedule(task, m * 1000);//to schedule the shutdown task so the program 
        //must end after m seconds
        this.startTime = System.currentTimeMillis();
        for (int i = 0; i < t; i++) {
            executorService.submit(threadArray[i]);
        }
    }
    
    public void printEdgesFormed(){
        int totalEdgesFormed = 0;
        for (int i = 1; i <= t; i++) {
            System.out.println("Thread " + i + " formed " + threadArray[i-1].getEdgesFormed() + " edges.");
            totalEdgesFormed += threadArray[i-1].getEdgesFormed();
        }
        System.out.println("Total edges formed for all threads: " + totalEdgesFormed);
    }
}
