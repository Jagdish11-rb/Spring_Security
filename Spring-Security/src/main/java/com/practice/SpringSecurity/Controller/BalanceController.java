package com.practice.SpringSecurity.Controller;

import com.practice.SpringSecurity.Entity.AccountTransactions;
import com.practice.SpringSecurity.Entity.Customer;
import com.practice.SpringSecurity.Repository.AccountTransactionsRepository;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/test-balance")
    public String testAccount(){
        return "Welcome to balance controller.";
    }

    @GetMapping("/myBalance")
    public ResponseEntity<?> getBalanceDetails(@RequestParam(name="id") int id, Authentication authentication) {
        List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                findByCustomerIdOrderByTransactionDtDesc(id);
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if (accountTransactions != null ) {
            if(customer.getId()==id){
                return new ResponseEntity<>(accountTransactions, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Can't view this page", HttpStatus.UNAUTHORIZED);
            }
        }else {
            return new ResponseEntity<>("Unable to fetch", HttpStatus.OK);
        }
    }
}