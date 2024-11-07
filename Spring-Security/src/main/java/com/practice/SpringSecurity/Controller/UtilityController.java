package com.practice.SpringSecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilityController {

    @GetMapping("/invalidSession")
    public String invalidSession(){
        return "Invalid session . Kindly login again";
    }

    @GetMapping("/expired")
    public String expiredSession(){
        return "Session expired !";
    }
}