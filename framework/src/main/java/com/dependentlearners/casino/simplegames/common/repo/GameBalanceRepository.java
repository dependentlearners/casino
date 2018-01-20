package com.dependentlearners.casino.simplegames.common.repo;

import com.dependentlearners.casino.simplegames.common.entity.BalanceEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GameBalanceRepository {

    private final Map<String, BalanceEntity> balanceStore;

    public GameBalanceRepository() {
        balanceStore = new HashMap<String, BalanceEntity>();
    }

    public void deduct(String userId, BigDecimal amount){
        BalanceEntity balanceEntity;
        if(this.balanceStore.containsKey(userId)){
            balanceEntity = this.balanceStore.get(userId);
            balanceEntity.setAmountPlayed(balanceEntity.getAmountPlayed().add(amount));
        }else{
            balanceEntity = new BalanceEntity();
            balanceEntity.setAmountPlayed(amount);
            balanceEntity.setAmountWon(BigDecimal.ZERO);
        }
        this.balanceStore.put(userId, balanceEntity);
    }

    public void credit(String userId, BigDecimal amount){
        BalanceEntity balanceEntity;
        if(this.balanceStore.containsKey(userId)){
            balanceEntity = balanceStore.get(userId);
            balanceEntity.setAmountWon(balanceEntity.getAmountWon().add(amount));
        }else{
            balanceEntity = new BalanceEntity();
            balanceEntity.setAmountPlayed(BigDecimal.ZERO);
            balanceEntity.setAmountWon(amount);
        }
        this.balanceStore.put(userId, balanceEntity);
    }

    public BalanceEntity getBalance(String userId){
        return this.balanceStore.get(userId);
    }
}
