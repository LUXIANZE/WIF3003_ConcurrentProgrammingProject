package com.assignment.concurrent.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class Point {
    private double x;
    private double y;
    private boolean hasEdge = false;
    private ReentrantLock lock = new ReentrantLock();
    
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public void unlock(){
        if(lock.isHeldByCurrentThread()){
            lock.unlock();
        }
    }
    
    //for hashSet use
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(x)
            .append(y)
            .toHashCode();
    }   
    
    //for hashSet use
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            final Point point = (Point) obj;
            return new EqualsBuilder()
                .append(x, point.x)
                .append(y, point.y)
                .isEquals();
        } else {
            return false;
        }
    }
    
    @Override
    public String toString(){
        return ("x: " + x + ", y: " + y);
    }
}