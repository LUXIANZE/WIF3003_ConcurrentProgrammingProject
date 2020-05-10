package com.assignment.concurrent.controller;

import com.assignment.concurrent.domain.Greeting;
import com.assignment.concurrent.domain.HelloMessage;
import com.assignment.concurrent.service.ConqueringService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    private final ConqueringService conqueringService;

    public GreetingController(ConqueringService conqueringService) {
        this.conqueringService = conqueringService;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        conqueringService.start();
        return new Greeting("Salam Dunia, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
