package com.assignment.concurrent.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import com.assignment.concurrent.service.MessageService;

public class PointPairingTask implements Callable<List> {

    private static int totalEdgeFormed;
    private static long startTime;
    private boolean threadFail;
    private int failAttempts;
    private int numberOfPoints;
    private Point[] points;
    private List<Edge> edgeArrayList;
    private final MessageService messageService;

    public PointPairingTask(Point[] arr, MessageService messageService){
        this.points = arr;
        this.messageService = messageService;
        this.edgeArrayList = new ArrayList<Edge>();
    }

    @Override
    public List call() throws Exception {
        // TODO change the flow control accordingly, these are dummy values
        long start = 0;
        long now = 0;
        long target = 4;

        while( (now-start) < target ){
            edgeArrayList.add(formEdges());
            now++;
        }
        
        return edgeArrayList;
    }

    public int getEdgesFormed(){
        return edgeArrayList.size();
    }

    public int getFailAttempts(){
        return failAttempts;
    }
    
    private Edge formEdges() throws InterruptedException {
        Random r = new Random();
        Edge edgeFormed = null;

        while (edgeFormed == null) {
            // generate 2 random int
            int r1 = r.nextInt(points.length);
            int r2 = r.nextInt(points.length);
            while (r2 == r1) {
                r2 = r.nextInt(points.length);
            }
            // get two random points
            Point p1 = points[r1];
            Point p2 = points[r2];

            if (p1.getLock().tryLock() && p2.getLock().tryLock()){
                if(!p1.isHasEdge() && !p2.isHasEdge()){
                    try {
                        edgeFormed = new Edge(p1,p2);
                        p1.setHasEdge(true);
                        p2.setHasEdge(true);
                    } catch (Exception e) {
                        System.out.println(e);
                    }finally{
                        p1.getLock().unlock();
                        p2.getLock().unlock();
                    }
                }else{
                    failAttempts++;
                    p1.getLock().unlock();
                    p2.getLock().unlock();
                }
            }else{
                if(p1.getLock().isLocked()) p1.getLock().unlock();
                if(p2.getLock().isLocked()) p2.getLock().unlock();
                failAttempts++;
            }
        }
        return edgeFormed;
    }
}