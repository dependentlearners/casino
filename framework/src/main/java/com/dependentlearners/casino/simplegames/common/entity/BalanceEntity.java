package com.dependentlearners.casino.simplegames.common.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceEntity {
    private BigDecimal amountPlayed;
    private BigDecimal amountWon;
}
