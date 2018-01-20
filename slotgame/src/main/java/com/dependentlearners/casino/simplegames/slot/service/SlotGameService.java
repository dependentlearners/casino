package com.dependentlearners.casino.simplegames.slot.service;

import com.dependentlearners.casino.simplegames.slot.representation.PlayRepresentation;

public interface SlotGameService {
    PlayRepresentation play(String userId);
}
