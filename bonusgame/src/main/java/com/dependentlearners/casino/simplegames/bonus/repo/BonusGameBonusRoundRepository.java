package com.dependentlearners.casino.simplegames.bonus.repo;

import com.dependentlearners.casino.simplegames.common.util.RandomNumberGenerator;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

import static com.dependentlearners.casino.simplegames.bonus.util.BonusGameConstants.BOX_PICKING_MAX_BOXES;

@Repository
public class BonusGameBonusRoundRepository {

    private final Map<String, Integer> bonusRoundStore;

    private final RandomNumberGenerator numberGenerator;

    public BonusGameBonusRoundRepository(RandomNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        bonusRoundStore = new HashMap<String, Integer>();
    }

    public void grantBonusRound(String userId){
        int generatedNumberForEmptyBox = numberGenerator.generate(BOX_PICKING_MAX_BOXES);
        bonusRoundStore.put(userId, generatedNumberForEmptyBox);
    }

    public void resetBonusRound(String userId){
        bonusRoundStore.remove(userId);
    }

    public Boolean checkBonusRound(String userId){
        return bonusRoundStore.containsKey(userId);
    }

    public Boolean matchGuess(String userId, Integer guess){
        return bonusRoundStore.getOrDefault(userId, 0).equals(guess);
    }
}
