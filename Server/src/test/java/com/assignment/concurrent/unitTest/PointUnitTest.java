package com.assignment.concurrent.unitTest;

import com.assignment.concurrent.domain.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointUnitTest {
    @Test
    public void Compare2SameCoordinatesShouldReturnTrue() {
        Point point1 = new Point(1.1, 2.2);
        Point point2 = new Point(1.1, 2.2);

        assertEquals(point1, point2);
    }
}
