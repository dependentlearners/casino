package com.dependentlearners.casino.simplegames.bonus.representation;


import com.dependentlearners.casino.simplegames.bonus.util.BonusGameType;

@lombok.Getter
@lombok.Setter
public class BoxPickingRepresentation {

    private boolean won;
    private BalanceRepresentation balance;
    private BonusGameType gameType = BonusGameType.BOXPICKING;
}
