package com.dependentlearners.casino.simplegames.common.util;

import com.google.common.collect.Range;
import org.springframework.stereotype.Component;

@Component
public class RandomRangeGenerator {

    private final RandomNumberGenerator randomNumberGenerator;

    public RandomRangeGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public Range<Integer> generate(int winningProbability){
        int generate = randomNumberGenerator.generate(100 - winningProbability);
        return Range.closedOpen(generate, generate+winningProbability);
    }
}
