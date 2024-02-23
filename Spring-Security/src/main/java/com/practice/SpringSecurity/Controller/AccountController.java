package com.practice.SpringSecurity.Controller;

import com.practice.SpringSecurity.Entity.Accounts;
import com.practice.SpringSecurity.Entity.Customer;
import com.practice.SpringSecurity.Repository.AccountsRepository;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/test-account")
    public String testAccount(){
        return "Welcome to account controller.";
    }

    @GetMapping("/myAccount")
    public ResponseEntity<?> getAccountDetails(@RequestParam(name="id") int Id, Authentication authentication) {
        Accounts accounts = accountsRepository.findByCustomerId(Id);
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if (accounts != null ) {
            if(customer.getId()==Id){
                return new ResponseEntity<>(accounts, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Can't view this page", HttpStatus.UNAUTHORIZED);
            }
        }else {
            return new ResponseEntity<>("Unable to fetch", HttpStatus.OK);
        }
    }

}