package com.dependentlearners.casino.simplegames.bonus.representation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRepresentation {

    private String errorCode;
    private String errorMessage;
}
