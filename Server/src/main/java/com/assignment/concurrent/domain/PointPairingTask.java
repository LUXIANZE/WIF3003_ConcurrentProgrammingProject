package com.assignment.concurrent.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

import com.assignment.concurrent.service.PointsService;

public class PointPairingTask implements Callable<Edge> {

    private static int totalEdgeFormed;
    private static long startTime;
    private boolean threadFail;
    private int failAttempts;
    private int numberOfPoints;
    private Point[] points;
    private List<Edge> edgeArrayList;

    public PointPairingTask(Point[] arr){
        this.points = arr;
        this.edgeArrayList = new ArrayList<Edge>();
    }

    @Override
    public Edge call() throws Exception {
        long start = 0;
        long now = 0;
        long target = 2;

        while( (now-start) < target ){
            edgeArrayList.add(formEdges());
        }
        
        return null;
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
        int p1 = r.nextInt(points.length);
        int p2 = r.nextInt(points.length);

        // Try to acquire 2 points
        while(points[p1].isLocked() || points[p2].isLocked() || (!points[p1].isHasEdge() && !points[p2].isHasEdge())) {
            if(points[p1].isLocked()) p1 = r.nextInt(points.length);
            if(points[p2].isLocked()) p2 = r.nextInt(points.length);
            if (points[p1].isHasEdge()||points[p2].isHasEdge()) {
                failAttempts++;
            }
        }

        // form Edge using 2 points
        try {
            points[p1].getLock().lock();
            points[p2].getLock().lock();
            edgeFormed = new Edge(points[p1],points[p2]);
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            points[p1].unlock();
            points[p2].unlock();
        }
        return edgeFormed;
    }
}