package com.dependentlearners.casino.simplegames.bonus.controller;

import com.dependentlearners.casino.simplegames.bonus.representation.BonusGameRepresentation;
import com.dependentlearners.casino.simplegames.bonus.service.BonusGameBoxPickingService;
import com.dependentlearners.casino.simplegames.bonus.service.BonusGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("bonusgame")
@Validated
public class BonusGameController {

    private final BonusGameService gameService;
    private final BonusGameBoxPickingService boxPickingService;

    public BonusGameController(BonusGameService gameService, BonusGameBoxPickingService boxPickingService) {
        this.gameService = gameService;
        this.boxPickingService = boxPickingService;
    }

    @RequestMapping("/play/{userId}")
    public ResponseEntity<BonusGameRepresentation> play(@PathVariable @Size(min = 1, max=20, message = "user id must be of size between 1 and 20") String userId){
        return new ResponseEntity<BonusGameRepresentation>(new BonusGameRepresentation(gameService.play(userId)), HttpStatus.OK);
    }

    @RequestMapping("/play/{userId}/{guess}")
    public ResponseEntity<BonusGameRepresentation> boxPicking(@PathVariable("userId") @Size(min = 2, max=20, message = "user id must be of size between 1 and 20") String userId,
                                        @PathVariable("guess") @Min(value = 1, message = "box number must be greater than 1")
                                                               @Max(value = 5, message = "box number must not be grater than 5") Integer guess){
        return new ResponseEntity<BonusGameRepresentation>(new BonusGameRepresentation(boxPickingService.pick(userId, guess)), HttpStatus.OK);
    }
}
