package com.assignment.concurrent.domain;

public class Point {
    private double x;
    private double y;
    private boolean hasEdge = false;
    
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public boolean getHasEdge(){
        return hasEdge;
    }
    
    public void setHasEdge(){
        this.hasEdge = true;
    }
    
    public String toString(){
        return ("x: " + this.x + ". y: " + this.y + ". Edge formed: " + this.hasEdge);
    }
}