package com.practice.SpringSecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactsController {

    @GetMapping("/test-contact")
    public String testContacts(){
        return "Welcome to contact controller.";
    }

}