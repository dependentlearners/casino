package com.dependentlearners.casino.simplegames.common.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberGenerator {

    private final Random random;

    public RandomNumberGenerator() {
        this.random = new Random();
    }

    public int generate(int upperBound){
       return random.nextInt((upperBound+1)-1)+1;
    }
}
