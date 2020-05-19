package com.assignment.concurrent.controller;

import com.assignment.concurrent.domain.Board;
import com.assignment.concurrent.domain.UserInputMessage;
import com.assignment.concurrent.service.ConquerService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

@org.springframework.stereotype.Controller
public class ConquerController {

    private final ConquerService conquerService;

    public ConquerController(ConquerService conquerService) {
        this.conquerService = conquerService;
    }

    @MessageMapping("/user-input")
    @SendTo("/topic/board")
    public Board createBoard(UserInputMessage userInputMessage) throws Exception {
        Thread.sleep(1000); // simulated delay
        return conquerService.start(userInputMessage);
    }

}
