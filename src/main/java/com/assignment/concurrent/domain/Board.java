package com.assignment.concurrent.domain;

import lombok.Data;

import java.util.Set;

@Data
public class Board {
    private Set<Point> points;
}
