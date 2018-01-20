package com.dependentlearners.casino.simplegames.bonus.representation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BonusGameRepresentation {

    private ErrorRepresentation error;
    private Object result;

    public BonusGameRepresentation(Object result){
        this.result = result;
    }

    public BonusGameRepresentation(String errorCode, String errorMessage){
        this.error = new ErrorRepresentation(errorCode, errorMessage);
    }
}
