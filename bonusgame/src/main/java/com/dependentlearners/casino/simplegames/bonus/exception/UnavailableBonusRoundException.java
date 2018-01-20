package com.dependentlearners.casino.simplegames.bonus.exception;

public class UnavailableBonusRoundException extends RuntimeException {

    public UnavailableBonusRoundException(String userId){
        super("No bonus round granted for user='"+userId);
    }
}
