package com.assignment.concurrent.domain;

import lombok.Data;



@Data
public class Edge {

    private Point firstPoint;
    private Point secondPoint;
    private ThreadColor threadColor;

    public Edge(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public Point[] getEdgePoints(){
        return new Point[]{this.firstPoint, this.secondPoint};
    }
}
