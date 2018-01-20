package com.dependentlearners.casino.simplegames.bonus.service;

import com.dependentlearners.casino.simplegames.bonus.exception.NoMoreAttemptLeftException;
import com.dependentlearners.casino.simplegames.bonus.exception.UnavailableBonusRoundException;
import com.dependentlearners.casino.simplegames.bonus.representation.BalanceRepresentation;
import com.dependentlearners.casino.simplegames.bonus.representation.BoxPickingRepresentation;
import com.dependentlearners.casino.simplegames.bonus.repo.BonusGameBonusRoundRepository;
import com.dependentlearners.casino.simplegames.bonus.repo.BonusGameBoxPickingRepository;
import com.dependentlearners.casino.simplegames.common.entity.BalanceEntity;
import com.dependentlearners.casino.simplegames.common.repo.GameBalanceRepository;
import com.dependentlearners.casino.simplegames.common.service.OddsCalculatorService;
import org.springframework.stereotype.Service;

import static com.dependentlearners.casino.simplegames.bonus.util.BonusGameConstants.WINNING_AMOUNT;

@Service
public class BonusGameBoxPickingServiceImpl implements BonusGameBoxPickingService{

    private final BonusGameBonusRoundRepository bonusRoundRepository;
    private final GameBalanceRepository balanceRepository;
    private final BonusGameBoxPickingRepository boxPickingRepository;

    public BonusGameBoxPickingServiceImpl(OddsCalculatorService oddsCalculatorService, BonusGameBonusRoundRepository bonusRoundRepository, GameBalanceRepository balanceRepository, BonusGameBoxPickingRepository boxPickingRepository) {
        this.bonusRoundRepository = bonusRoundRepository;
        this.balanceRepository = balanceRepository;
        this.boxPickingRepository = boxPickingRepository;
    }

    @Override
    public BoxPickingRepresentation pick(String userId, Integer guess) {

        assertBonusRoundAvailable(userId);

        availChoosenBox(userId, guess);

        boolean won = creditIfMatched(userId, guess);

        return formRepresentation(userId, won);
    }

    private boolean creditIfMatched(String userId, Integer guess) {
        boolean won;
        if(!bonusRoundRepository.matchGuess(userId, guess)){
            balanceRepository.credit(userId, WINNING_AMOUNT);
            won = true;
        }else{
            resetStorage(userId);
            won = false;
        }
        return won;
    }

    private void resetStorage(String userId) {
        bonusRoundRepository.resetBonusRound(userId);
        boxPickingRepository.resetBox(userId);
    }

    private void availChoosenBox(String userId, Integer guess) {
        try{
            boxPickingRepository.availBox(userId, guess);
        }catch(NoMoreAttemptLeftException e){
            boxPickingRepository.resetBox(userId);
            bonusRoundRepository.resetBonusRound(userId);
            throw e;
        }
    }

    private void assertBonusRoundAvailable(String userId) {
        if(!bonusRoundRepository.checkBonusRound(userId)){
            throw new UnavailableBonusRoundException(userId);
        }
    }

    private BoxPickingRepresentation formRepresentation(String userId, boolean won) {
        BoxPickingRepresentation playRepresentation = new BoxPickingRepresentation();
        playRepresentation.setWon(won);
        BalanceRepresentation balanceRepresentation = new BalanceRepresentation();
        BalanceEntity balance = balanceRepository.getBalance(userId);
        balanceRepresentation.setAmountPlayed(balance.getAmountPlayed());
        balanceRepresentation.setAmountEarned(balance.getAmountWon());
        playRepresentation.setBalance(balanceRepresentation);
        return playRepresentation;
    }

}
