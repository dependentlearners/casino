package com.dependentlearners.casino.simplegames.bonus.util;

import java.math.BigDecimal;

public interface BonusGameConstants {

    Integer WINNING_PROBABILITY = 10;
    BigDecimal BETTING_AMOUNT = BigDecimal.TEN;
    BigDecimal WINNING_AMOUNT = BigDecimal.valueOf(5);
    Integer BOX_PICKING_MAX_ATTEMPTS = 5;
    Integer BOX_PICKING_MAX_BOXES = 5;
}
