package com.dependentlearners.casino.simplegames.slot.representation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SlotGameRepresentation {

    private ErrorRepresentation error;
    private Object result;

    public SlotGameRepresentation(Object result){
        this.result = result;
    }

    public SlotGameRepresentation(String errorCode, String errorMessage){
        this.error = new ErrorRepresentation(errorCode, errorMessage);
    }
}
