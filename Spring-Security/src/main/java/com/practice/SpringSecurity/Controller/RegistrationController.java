package com.practice.SpringSecurity.Controller;

import com.practice.SpringSecurity.Entity.Customer;
import com.practice.SpringSecurity.Exception.CustomException;
import com.practice.SpringSecurity.POJO.LoginRequest;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
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

    @GetMapping("/log-in")
    public String getUserDetailsAfterLogin(Authentication authentication){
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if(customer!=null){
            return "Logged in successfully.";
        }else{
            return "Unable to login. Try again later.";
        }
    }

    @GetMapping("/get")
    public String get(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("Cookie name : "+cookie.getName()+"Cookie value : "+cookie.getValue());
            }
        }
        return "S";
    }
}
