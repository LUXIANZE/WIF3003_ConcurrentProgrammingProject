package com.assignment.concurrent.unitTest;

import com.assignment.concurrent.domain.Coordinate;
import com.assignment.concurrent.util.PointsUtil;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PointsUtilTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void createPointsShouldNotContainDuplicateValues() {
        int numOfPoints = 5000;
        Collection collection = PointsUtil.createPoints(numOfPoints);

        assertEquals(collection.size() , numOfPoints);
        assertEquals(new HashSet<Double>(collection).size(), numOfPoints);
    }

    @Test
    public void popShouldRemoveSelectedElementFromSet() {
        int numOfPoints = 5000;
        Set<Coordinate> set = PointsUtil.createPoints(numOfPoints);

        Coordinate coordinate = PointsUtil.pop(set);
        assertEquals(set.size(), numOfPoints-1);
        assertFalse(set.contains(coordinate));
    }
}