package com.assignment.concurrent.domain;

import lombok.Data;



@Data
public class Edge {

    private Point firstPoint;
    private Point secondPoint;

    public Edge(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public Point[] getEdegePoints(){
        return new Point[]{this.firstPoint, this.secondPoint};
    }
}
