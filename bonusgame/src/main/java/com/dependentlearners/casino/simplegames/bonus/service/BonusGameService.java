package com.dependentlearners.casino.simplegames.bonus.service;

import com.dependentlearners.casino.simplegames.bonus.representation.PlayRepresentation;

public interface BonusGameService {

    PlayRepresentation play(String userId);

}
