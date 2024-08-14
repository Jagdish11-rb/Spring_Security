package com.practice.SpringSecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @GetMapping("/test-notice")
    public String testNotices(){
        return "Welcome to notice controller.";
    }

}