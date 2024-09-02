package com.practice.SpringSecurity.Component;

import com.practice.SpringSecurity.Entity.Customer;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByCustomerName(username);
        if(customer!=null){
            return new User(customer.getCustomerName(), customer.getPassword(), Collections.singleton(new SimpleGrantedAuthority(customer.getRole())));
        }else{
            throw new UsernameNotFoundException("User not found with username : "+username);
        }
    }
}
