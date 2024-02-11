package com.paccy.springsecurityclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @RequestMapping("/hello")
    public String greetings(){
        return "Hello guys";
    }
}
