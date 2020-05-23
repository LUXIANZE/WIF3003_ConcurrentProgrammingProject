package com.assignment.concurrent.domain;

import java.util.concurrent.locks.ReentrantLock;

public class Point {
    private double x;
    private double y;
    private boolean hasEdge = false;
    private ReentrantLock lock = new ReentrantLock();
    
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public boolean getHasEdge(){
        return hasEdge;
    }
    
    public void setHasEdge(){
        this.hasEdge = true;
    }
    
    public boolean isLocked(){
        return lock.isLocked();
    }
    
    public void unlock(){
        if(lock.isHeldByCurrentThread()){
            lock.unlock();
        }
    }
    
    @Override
    public String toString(){
        return ("x: " + x + ", y: " + y);
    }
}