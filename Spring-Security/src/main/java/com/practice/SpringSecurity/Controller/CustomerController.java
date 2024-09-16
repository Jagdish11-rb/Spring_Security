package com.practice.SpringSecurity.Controller;

import com.practice.SpringSecurity.Component.CustomPasswordChecker;
import com.practice.SpringSecurity.Entity.Customer;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.authentication.password.CompromisedPasswordException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final CustomPasswordChecker customPasswordChecker;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody Customer customer) {
        CompromisedPasswordDecision compromisedPasswordDecision = customPasswordChecker.check(customer.getPassword());
        if (compromisedPasswordDecision.isCompromised()) {
            throw new CompromisedPasswordException("Provided password is compromised and can't be used.");
        }
        boolean isUsernameExists = customerRepository.existsByCustomerName(customer.getCustomerName());
        if (isUsernameExists) {
            throw new BadCredentialsException("User name already taken.");
        }
        String password = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(password);
        customerRepository.save(customer);
        return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
    }

}