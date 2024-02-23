package com.practice.SpringSecurity.Config;//package com.practice.SpringSecurity.Config;

import com.practice.SpringSecurity.Entity.Customer;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The below SecurityUserDetails is the custom authentication using DaoAuthenticationProvider.
 */

/*@Service
public class SecurityUserDetails implements UserDetailsService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if(customer==null){
            throw new UsernameNotFoundException("User details not found with this given email : "+email);
        }else{
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customer.getRole()));
            return new User(customer.getEmail(),customer.getPassword(),authorities);
        }
    }
}*/
