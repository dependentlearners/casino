package com.dependentlearners.casino.simplegames.slot.representation;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BalanceRepresentation {

    private BigDecimal amountPlayed;
    private BigDecimal amountEarned;

}
