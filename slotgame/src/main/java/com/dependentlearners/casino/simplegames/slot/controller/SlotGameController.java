package com.dependentlearners.casino.simplegames.slot.controller;

import com.dependentlearners.casino.simplegames.slot.representation.SlotGameRepresentation;
import com.dependentlearners.casino.simplegames.slot.service.SlotGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;

@RestController
@RequestMapping("slotgame")
@Validated
public class SlotGameController {

    private final SlotGameService slotGameService;

    public SlotGameController(SlotGameService slotGameService) {
        this.slotGameService = slotGameService;
    }

    @RequestMapping("/play/{userId}")
    public ResponseEntity<SlotGameRepresentation> play(@PathVariable @Size(min = 1, max=20, message = "user id must be of size between 1 and 20") String userId){
       return new ResponseEntity<SlotGameRepresentation>(new SlotGameRepresentation(slotGameService.play(userId)), HttpStatus.OK);
    }
}
