package com.practice.SpringSecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/test-card")
    public String testCards(){
        return "Welcome to card controller.";
    }

}