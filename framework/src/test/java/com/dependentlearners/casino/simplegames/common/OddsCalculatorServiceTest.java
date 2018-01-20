package com.dependentlearners.casino.simplegames.common;


import com.dependentlearners.casino.simplegames.common.service.OddsCalculatorService;
import com.dependentlearners.casino.simplegames.common.util.RandomNumberGenerator;
import com.dependentlearners.casino.simplegames.common.util.RandomRangeGenerator;
import org.junit.Before;
import org.junit.Test;

public class OddsCalculatorServiceTest {

    private OddsCalculatorService calculator;

    @Before
    public void init(){
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        calculator = new OddsCalculatorService(randomNumberGenerator, new RandomRangeGenerator(randomNumberGenerator));
    }

    @Test
    public void calculate()  {
        System.out.println(calculator.calculate(10));
    }

}
