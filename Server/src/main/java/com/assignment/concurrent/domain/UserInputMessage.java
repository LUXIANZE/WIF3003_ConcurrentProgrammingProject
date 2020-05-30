package com.assignment.concurrent.domain;

import lombok.Data;

@Data
public class UserInputMessage {
    private Integer n;
    private Integer m;
    private Integer t;

    public Integer getN(){
        return n;
    }
}
