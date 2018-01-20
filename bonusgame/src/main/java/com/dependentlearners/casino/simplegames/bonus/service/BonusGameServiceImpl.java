package com.dependentlearners.casino.simplegames.bonus.service;

import com.dependentlearners.casino.simplegames.bonus.repo.BonusGameBonusRoundRepository;
import com.dependentlearners.casino.simplegames.bonus.representation.BalanceRepresentation;
import com.dependentlearners.casino.simplegames.bonus.representation.PlayRepresentation;
import com.dependentlearners.casino.simplegames.bonus.util.BonusGameConstants;
import com.dependentlearners.casino.simplegames.common.entity.BalanceEntity;
import com.dependentlearners.casino.simplegames.common.repo.GameBalanceRepository;
import com.dependentlearners.casino.simplegames.common.service.OddsCalculatorService;
import org.springframework.stereotype.Service;

import static com.dependentlearners.casino.simplegames.bonus.util.BonusGameConstants.WINNING_PROBABILITY;

@Service
public class BonusGameServiceImpl implements BonusGameService{

    private final OddsCalculatorService oddsCalculatorService;
    private final BonusGameBonusRoundRepository bonusRoundRepository;
    private final GameBalanceRepository balanceRepository;

    public BonusGameServiceImpl(OddsCalculatorService oddsCalculatorService, BonusGameBonusRoundRepository bonusRoundRepository, GameBalanceRepository balanceRepository) {
        this.oddsCalculatorService = oddsCalculatorService;
        this.bonusRoundRepository = bonusRoundRepository;
        this.balanceRepository = balanceRepository;
    }

    @Override
    public PlayRepresentation play(String userId) {

        deductIfApplicable(userId);

        boolean calculateBonusRound = oddsCalculatorService.calculate(WINNING_PROBABILITY);

        if(calculateBonusRound) bonusRoundRepository.grantBonusRound(userId);
        else bonusRoundRepository.resetBonusRound(userId);

        return formRepresentation(userId, calculateBonusRound);
    }

    private PlayRepresentation formRepresentation(String userId, boolean calculateBonusRound) {
        PlayRepresentation playRepresentation = new PlayRepresentation();
        playRepresentation.setEarnedBonusRound(calculateBonusRound);
        BalanceRepresentation balanceRepresentation = new BalanceRepresentation();
        BalanceEntity balance = balanceRepository.getBalance(userId);
        balanceRepresentation.setAmountPlayed(balance.getAmountPlayed());
        balanceRepresentation.setAmountEarned(balance.getAmountWon());
        playRepresentation.setBalance(balanceRepresentation);
        return playRepresentation;
    }

    private void deductIfApplicable(String userId) {
        balanceRepository.deduct(userId, BonusGameConstants.BETTING_AMOUNT);
    }
}
