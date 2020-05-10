package com.assignment.concurrent.unitTest;

import com.assignment.concurrent.domain.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinateUnitTest {
    @Test
    public void Compare2SameCoordinatesShouldReturnTrue() {
        Coordinate coordinate1 = new Coordinate();
        Coordinate coordinate2 = new Coordinate();

        coordinate1.setX(11.01);
        coordinate1.setY(22.01);
        coordinate2.setX(11.01);
        coordinate2.setY(22.01);

        assertEquals(coordinate1, coordinate2);
    }
}
