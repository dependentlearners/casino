package com.dependentlearners.casino.simplegames.bonus.exception;

public class AlreadyAvailedBoxException extends RuntimeException {

    public AlreadyAvailedBoxException(int guess){
        super("Box "+ guess+" already availed");
    }
}
