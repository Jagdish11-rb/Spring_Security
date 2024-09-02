package com.practice.SpringSecurity.Controller;

import com.practice.SpringSecurity.Entity.Customer;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final PasswordEncoder passwordEncoder;

    private final CustomerRepository customerRepository;


    @PostMapping("/create-user")
   public ResponseEntity<?> createUser(@RequestBody Customer customer){
        try{
            String password = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(password);
            customerRepository.save(customer);
            return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Unable to create user", HttpStatus.CONFLICT);
        }
   }

}