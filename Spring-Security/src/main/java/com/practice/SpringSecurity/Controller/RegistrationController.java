package com.practice.SpringSecurity.Controller;

import com.practice.SpringSecurity.Entity.Customer;
import com.practice.SpringSecurity.Exception.CustomException;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/test-register")
    public String testAccount(){
        return "Welcome to registration controller.";
    }

    @PostMapping("/register")
    public String registerCustomer(@Valid @RequestBody Customer customer,BindingResult validation){
        try{
            if(validation.hasErrors())
                throw new CustomException(validation.getFieldError().getDefaultMessage());

            Customer cust = customerRepository.findByEmail(customer.getEmail());

            if(cust!=null)
                throw new Exception("User already exists.");

            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            customer.setCreated_date(String.valueOf(new Date(System.currentTimeMillis())));

            Customer savedCustomer = customerRepository.save(customer);

            if(savedCustomer==null)
                throw new Exception("Unable to register customer. Try again.");

            return "Customer registered successfully. Thank you.";
        }catch(CustomException ce){
            return "A custom exception occurred due to : "+ce.getMessage();
        }catch(Exception e){
            return "An exception occurred due to : "+e.getMessage();
        }
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication){
        Customer customer = customerRepository.findByEmail(authentication.getName());
        return customer;
    }

}
