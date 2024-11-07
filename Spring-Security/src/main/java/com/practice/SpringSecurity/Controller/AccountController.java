package com.practice.SpringSecurity.Controller;


import com.practice.SpringSecurity.Entity.Customer;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;

@RestController
public class AccountController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/test-account")
    public String testAccount() {
        return "Welcome to account controller.";
    }

    @GetMapping("/get-customer-details")
    public ResponseEntity<?> getDetails(Authentication authentication) {
        String username = authentication.getName();
        Customer customer = customerRepository.findByCustomerName(username);
        if (customer == null) {
            return new ResponseEntity<>("User not found with username : " + username, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}