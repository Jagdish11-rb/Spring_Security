package com.practice.SpringSecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/test-balance")
    public String testBalance(){
        return "Welcome to balance controller.";
    }

}