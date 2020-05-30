package com.assignment.concurrent.service;

import com.assignment.concurrent.domain.Point;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

@Service
public class PointsService {

    public static Set<Point> generatePoints(int numOfPoints) {
        Set<Point> points = new HashSet<>();
        while (points.size() < numOfPoints) {
            Point point = new Point(randomDouble(), randomDouble());
            points.add(point);
        }
        return points;
    }

    public static Double randomDouble() {
        Double value = new Random().nextDouble()*1000;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(value));
    }
}
