package com.practice.SpringSecurity.Controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @GetMapping("/test-account")
    public String testAccount(){
        return "Welcome to account controller.";
    }

}