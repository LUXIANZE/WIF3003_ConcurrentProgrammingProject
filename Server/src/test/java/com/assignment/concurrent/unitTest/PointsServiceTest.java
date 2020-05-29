package com.assignment.concurrent.unitTest;

import com.assignment.concurrent.domain.Point;
import com.assignment.concurrent.service.PointsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PointsServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void createPointsShouldNotContainDuplicateValues() {
        int numOfPoints = 5000;
        Collection collection = PointsService.generatePoints(numOfPoints);

        assertEquals(collection.size() , numOfPoints);
        assertEquals(new HashSet<Double>(collection).size(), numOfPoints);
    }

    @Test
    public void popShouldRemoveSelectedElementFromSet() {
        int numOfPoints = 5000;
        Set<Point> set = PointsService.generatePoints(numOfPoints);

        // Point point = PointsService.pop(set);
        assertEquals(set.size(), numOfPoints-1);
        // assertFalse(set.contains(point));
    }
}