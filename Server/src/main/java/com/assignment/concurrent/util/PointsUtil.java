package com.assignment.concurrent.util;

import com.assignment.concurrent.domain.Point;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

@Component
public class PointsUtil {

    public static Set<Point> createPoints(int numOfPoints) {
        //HashSet does not allow duplicate elements
        Set<Point> points = new HashSet<>();
        while (points.size() < numOfPoints) {
            Point point = new Point(randomDouble(), randomDouble());
            points.add(point);
        }
        return points;
    }

    public static synchronized Point pop(Set<Point> points) {

        int randomNumber = new Random().nextInt(points.size());

        //get an iterator
        Iterator<? extends Point> iterator = points.iterator();

        int currentIndex = 0;
        Point randomElement = null;

        //iterate the HashSet
        while(iterator.hasNext()){
            //if current index is equal to random number
            if(currentIndex == randomNumber) {
                randomElement = iterator.next();
                points.remove(randomElement);
                return randomElement;
            }

            currentIndex++;
        }

        return randomElement;
    }

    private static Double randomDouble() {
        Double value = new Random().nextDouble()*1000;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(value));
    }
}
