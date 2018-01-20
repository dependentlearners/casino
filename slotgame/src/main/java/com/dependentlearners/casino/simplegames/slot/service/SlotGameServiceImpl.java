package com.dependentlearners.casino.simplegames.slot.service;

import com.dependentlearners.casino.simplegames.common.entity.BalanceEntity;
import com.dependentlearners.casino.simplegames.common.repo.GameBalanceRepository;
import com.dependentlearners.casino.simplegames.common.service.OddsCalculatorService;
import com.dependentlearners.casino.simplegames.slot.repo.SlotGameFreeRoundRepository;
import com.dependentlearners.casino.simplegames.slot.representation.BalanceRepresentation;
import com.dependentlearners.casino.simplegames.slot.representation.PlayRepresentation;
import com.dependentlearners.casino.simplegames.slot.util.SlotGameConstants;
import com.dependentlearners.casino.simplegames.slot.util.SlotGameType;
import org.springframework.stereotype.Service;

@Service
public class SlotGameServiceImpl implements SlotGameService{

    private final OddsCalculatorService oddsCalculatorService;
    private final SlotGameFreeRoundRepository slotGameFreeRoundRepository;
    private final GameBalanceRepository gameBalanceRepository;

    public SlotGameServiceImpl(OddsCalculatorService oddsCalculatorService, SlotGameFreeRoundRepository slotGameFreeRoundRepository, GameBalanceRepository gameBalanceRepository) {
        this.oddsCalculatorService = oddsCalculatorService;
        this.slotGameFreeRoundRepository = slotGameFreeRoundRepository;
        this.gameBalanceRepository = gameBalanceRepository;
    }

    @Override
    public PlayRepresentation play(String userId) {

        boolean isNormalRound = deductIfApplicable(userId);

        boolean calculateWin = oddsCalculatorService.calculate(SlotGameConstants.WINNING_PROBABILITY);
        creditIfApplicable(userId, calculateWin);

        boolean calculateFreeRound = oddsCalculatorService.calculate(SlotGameConstants.FREE_ROUND_WINNING_PROBABILITY);
        if(calculateFreeRound) slotGameFreeRoundRepository.grantFreeRound(userId);
        else slotGameFreeRoundRepository.resetFreeRound(userId);

        return formRepresentation(userId, calculateWin, calculateFreeRound, isNormalRound);
    }

    private PlayRepresentation formRepresentation(String userId, boolean calculateWin, boolean calculateFreeRound, boolean isNormalRound) {
        PlayRepresentation playRepresentation = new PlayRepresentation();
        playRepresentation.setWon(calculateWin);
        playRepresentation.setEarnedFreeRound(calculateFreeRound);
        BalanceRepresentation balanceRepresentation = new BalanceRepresentation();
        BalanceEntity balance = gameBalanceRepository.getBalance(userId);
        balanceRepresentation.setAmountPlayed(balance.getAmountPlayed());
        balanceRepresentation.setAmountEarned(balance.getAmountWon());
        playRepresentation.setBalance(balanceRepresentation);
        playRepresentation.setGameType(isNormalRound? SlotGameType.NORMAL:SlotGameType.FREE);
        return playRepresentation;
    }

    private void creditIfApplicable(String userId, boolean hasWon) {
        if(hasWon){
            gameBalanceRepository.credit(userId, SlotGameConstants.WINNING_AMOUNT);
        }
    }

    private boolean deductIfApplicable(String userId) {
        if(!slotGameFreeRoundRepository.checkFreeRound(userId)){
            gameBalanceRepository.deduct(userId, SlotGameConstants.BETTING_AMOUNT);
            return true;
        }
        return false;
    }
}
