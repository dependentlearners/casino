package com.dependentlearners.casino.simplegames.bonus.repo;

import com.dependentlearners.casino.simplegames.bonus.exception.AlreadyAvailedBoxException;
import com.dependentlearners.casino.simplegames.bonus.exception.NoMoreAttemptLeftException;
import com.google.common.collect.Lists;
import com.dependentlearners.casino.simplegames.bonus.util.BonusGameConstants;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BonusGameBoxPickingRepository {

    private final Map<String, List<Integer>> boxPickingStore;

    public BonusGameBoxPickingRepository() {
        boxPickingStore = new HashMap<>();
    }

    public void availBox(String userId, Integer guess){
        List<Integer> history = boxPickingStore.getOrDefault(userId, Lists.newArrayList());
        if(history.contains(guess)){
            throw new AlreadyAvailedBoxException(guess);
        }
        history.add(guess);
        boxPickingStore.put(userId, history);
        if(BonusGameConstants.BOX_PICKING_MAX_ATTEMPTS < history.size()){
            throw new NoMoreAttemptLeftException(BonusGameConstants.BOX_PICKING_MAX_ATTEMPTS);
        }
    }

    public void resetBox(String userId){
        boxPickingStore.remove(userId);
    }
}
