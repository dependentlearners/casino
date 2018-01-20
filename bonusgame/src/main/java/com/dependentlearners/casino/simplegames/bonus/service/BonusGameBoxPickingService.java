package com.dependentlearners.casino.simplegames.bonus.service;

import com.dependentlearners.casino.simplegames.bonus.representation.BoxPickingRepresentation;

public interface BonusGameBoxPickingService {
    BoxPickingRepresentation pick(String userId, Integer guess);
}
