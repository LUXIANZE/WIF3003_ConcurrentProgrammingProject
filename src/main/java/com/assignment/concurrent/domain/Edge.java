package com.assignment.concurrent.domain;

import lombok.Data;

import java.util.Set;

@Data
public class Edge {

    public Edge(Set<Point> points) {
        this.points = points;
    }

    private Set<Point> points;
}
