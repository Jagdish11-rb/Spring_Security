package com.practice.SpringSecurity.Controller;

import com.practice.SpringSecurity.Entity.Cards;
import com.practice.SpringSecurity.Entity.Customer;
import com.practice.SpringSecurity.Repository.CardsRepository;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/test-cards")
    public String testAccount(){
        return "Welcome to cards controller.";
    }

    @GetMapping("/myCards")
    public ResponseEntity<?> getCardDetails(@RequestParam(name="id") int id, Authentication authentication) {
        List<Cards> cardDetails = cardsRepository.findByCustomerId(id);
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if (cardDetails != null ) {
            if(customer.getId()==id){
                return new ResponseEntity<>(cardDetails, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Can't view this page.", HttpStatus.UNAUTHORIZED);
            }
        }else {
            return new ResponseEntity<>("Unable to fetch", HttpStatus.OK);
        }
    }

}
