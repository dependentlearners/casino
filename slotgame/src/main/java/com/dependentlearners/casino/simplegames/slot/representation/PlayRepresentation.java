package com.dependentlearners.casino.simplegames.slot.representation;


import com.dependentlearners.casino.simplegames.slot.util.SlotGameType;

@lombok.Getter
@lombok.Setter
public class PlayRepresentation {

    private boolean won;
    private boolean earnedFreeRound;
    private BalanceRepresentation balance;
    private SlotGameType gameType;
}
