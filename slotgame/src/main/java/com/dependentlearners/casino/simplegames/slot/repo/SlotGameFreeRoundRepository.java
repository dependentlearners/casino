package com.dependentlearners.casino.simplegames.slot.repo;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SlotGameFreeRoundRepository {

    private final Map<String, Boolean> freeRoundStore;

    public SlotGameFreeRoundRepository() {
        freeRoundStore = new HashMap<String, Boolean>();
    }

    public void grantFreeRound(String userId){
        freeRoundStore.put(userId, true);
    }

    public void resetFreeRound(String userId){
        freeRoundStore.put(userId, false);
    }

    public Boolean checkFreeRound(String userId){
        if(!freeRoundStore.containsKey(userId)){
            return false;
        }
        return freeRoundStore.get(userId);
    }
}
