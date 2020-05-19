package com.assignment.concurrent.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Data
public class Point {

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    private Double x;
    private Double y;

    //for hashSet use
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(x)
                .append(y)
                .toHashCode();
    }

    //for hashSet use
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            final Point point = (Point) obj;

            return new EqualsBuilder()
                    .append(x, point.x)
                    .append(y, point.y)
                    .isEquals();
        } else {
            return false;
        }
    }
}
