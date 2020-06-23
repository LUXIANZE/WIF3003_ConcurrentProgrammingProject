package com.assignment.concurrent.domain;

import lombok.Data;

@Data
public class ThreadColor {

    private int R = (int)(Math.random( )*256);
    private int G = (int)(Math.random( )*256);
    private int B= (int)(Math.random( )*256);
}
