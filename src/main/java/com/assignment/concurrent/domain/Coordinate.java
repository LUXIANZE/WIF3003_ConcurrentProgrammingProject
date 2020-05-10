package com.assignment.concurrent.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Data
public class Coordinate {
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
        if (obj instanceof Coordinate) {
            final Coordinate coordinate = (Coordinate) obj;

            return new EqualsBuilder()
                    .append(x, coordinate.x)
                    .append(y, coordinate.y)
                    .isEquals();
        } else {
            return false;
        }
    }
}
