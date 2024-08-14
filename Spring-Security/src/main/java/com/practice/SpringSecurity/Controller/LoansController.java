package com.practice.SpringSecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @GetMapping("/test-loan")
    public String testLoans(){
        return "Welcome to loan controller.";
    }

}