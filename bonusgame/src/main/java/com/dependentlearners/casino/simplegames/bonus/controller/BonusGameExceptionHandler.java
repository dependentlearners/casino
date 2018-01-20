package com.dependentlearners.casino.simplegames.bonus.controller;

import com.dependentlearners.casino.simplegames.bonus.exception.AlreadyAvailedBoxException;
import com.dependentlearners.casino.simplegames.bonus.exception.NoMoreAttemptLeftException;
import com.dependentlearners.casino.simplegames.bonus.exception.UnavailableBonusRoundException;
import com.dependentlearners.casino.simplegames.bonus.representation.BonusGameRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class BonusGameExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BonusGameRepresentation> handleNoMoreAttemptLeftException(ConstraintViolationException e){
        return new ResponseEntity<BonusGameRepresentation>(new BonusGameRepresentation("400", e.getConstraintViolations().iterator().next().getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoMoreAttemptLeftException.class)
    public ResponseEntity<BonusGameRepresentation> handleNoMoreAttemptLeftException(NoMoreAttemptLeftException e){
        return new ResponseEntity<BonusGameRepresentation>(new BonusGameRepresentation("400", e.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnavailableBonusRoundException.class)
    public ResponseEntity<BonusGameRepresentation> handleUnavailableBonusRoundException(UnavailableBonusRoundException e){
        return new ResponseEntity<BonusGameRepresentation>(new BonusGameRepresentation("404", e.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyAvailedBoxException.class)
    public ResponseEntity<BonusGameRepresentation> handleAlreadyAvailedBoxException(AlreadyAvailedBoxException e){
        return new ResponseEntity<BonusGameRepresentation>(new BonusGameRepresentation("403", e.getMessage()),HttpStatus.FORBIDDEN);
    }
}
