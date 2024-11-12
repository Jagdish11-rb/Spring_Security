package com.practice.SpringSecurity.Controller;

import com.practice.SpringSecurity.Pojo.CsrfRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/csrf-testing")
    public ResponseEntity<?> testCsrf(@RequestBody CsrfRequest csrfRequest, Authentication authentication){
        return new ResponseEntity<>("CSRF CONFIGURATION WORKING : "+authentication.getName(),HttpStatus.OK);
    }
}