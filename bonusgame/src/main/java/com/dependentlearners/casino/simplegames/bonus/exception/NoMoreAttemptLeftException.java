package com.dependentlearners.casino.simplegames.bonus.exception;

public class NoMoreAttemptLeftException extends RuntimeException {

    public NoMoreAttemptLeftException(int maxAttempts){
        super("Not more game attempts left , max attempt "+ maxAttempts+ " reached");
    }
}
