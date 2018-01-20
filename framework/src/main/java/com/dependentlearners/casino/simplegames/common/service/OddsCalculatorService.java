package com.dependentlearners.casino.simplegames.common.service;

import com.dependentlearners.casino.simplegames.common.util.RandomNumberGenerator;
import com.dependentlearners.casino.simplegames.common.util.RandomRangeGenerator;
import com.google.common.collect.Range;
import org.springframework.stereotype.Component;

@Component
public class OddsCalculatorService {

    private final RandomNumberGenerator randomNumberGenerator;
    private final RandomRangeGenerator randomRangeGenerator;

    public OddsCalculatorService(RandomNumberGenerator randomNumberGenerator, RandomRangeGenerator randomRangeGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.randomRangeGenerator = randomRangeGenerator;
    }

    public boolean calculate(int winningProbability){
        int generatedValue = randomNumberGenerator.generate(100);
        Range<Integer> generatedRange = randomRangeGenerator.generate(winningProbability);
        return generatedRange.contains(generatedValue);
    }
}
