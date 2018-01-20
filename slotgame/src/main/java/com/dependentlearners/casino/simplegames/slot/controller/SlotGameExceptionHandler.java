package com.dependentlearners.casino.simplegames.slot.controller;

import com.dependentlearners.casino.simplegames.slot.representation.SlotGameRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class SlotGameExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<SlotGameRepresentation> handleNoMoreAttemptLeftException(ConstraintViolationException e){
        return new ResponseEntity<SlotGameRepresentation>(new SlotGameRepresentation("400", e.getConstraintViolations().iterator().next().getMessage()),HttpStatus.BAD_REQUEST);
    }
}
