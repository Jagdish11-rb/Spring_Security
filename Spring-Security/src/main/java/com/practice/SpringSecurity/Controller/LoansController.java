package com.practice.SpringSecurity.Controller;

import com.practice.SpringSecurity.Entity.Customer;
import com.practice.SpringSecurity.Entity.Loans;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import com.practice.SpringSecurity.Repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/test-loans")
    public String testAccount(){
        return "Welcome to loans controller.";
    }

    @GetMapping("/myLoans")
    public ResponseEntity<?> getLoanDetails(@RequestParam(name="id")int id, Authentication authentication) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if (loans != null ) {
            if(customer.getId()==id){
                return new ResponseEntity<>(loans, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Can't view this page", HttpStatus.UNAUTHORIZED);
            }
        }else {
            return new ResponseEntity<>("Unable to fetch", HttpStatus.OK);
        }
    }

}
