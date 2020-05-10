package com.assignment.concurrent.util;

import com.assignment.concurrent.domain.Coordinate;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

@Component
public class PointsUtil {

    public static Set<Coordinate> createPoints(int numOfPoints) {
        //HashSet does not allow duplicate elements
        Set<Coordinate> points = new HashSet<>();
        while (points.size() < numOfPoints) {
            Coordinate coordinate = new Coordinate();
            coordinate.setX(randomDouble());
            coordinate.setY(randomDouble());
            points.add(coordinate);
        }
        return points;
    }

    public static synchronized Coordinate pop(Set<Coordinate> points) {

        int randomNumber = new Random().nextInt(points.size());

        //get an iterator
        Iterator<? extends Coordinate> iterator = points.iterator();

        int currentIndex = 0;
        Coordinate randomElement = null;

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
