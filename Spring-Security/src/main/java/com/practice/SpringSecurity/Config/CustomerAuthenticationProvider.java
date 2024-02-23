package com.practice.SpringSecurity.Config;

import com.practice.SpringSecurity.Entity.Authority;
import com.practice.SpringSecurity.Entity.Customer;
import com.practice.SpringSecurity.Repository.AuthorityRepository;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * The below CustomerAuthenticationProvider is our custom authentication using our custom authentication provider
 */
@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Customer customer = customerRepository.findByEmail(email);
        if(customer!=null){
            if(passwordEncoder.matches(password,customer.getPassword())){

//                List<GrantedAuthority> authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority(customer.getRole()));

                System.out.println("An user with authorities : "+getGrantedAuthority(customer.getId())+" tried to access your application.");
                return new UsernamePasswordAuthenticationToken(email,password,getGrantedAuthority(customer.getId()));
            }else{
                throw new BadCredentialsException("Invalid credentials.");
            }
        }else{
            throw new BadCredentialsException("No customer registered with this email_id.");
        }
    }

    private List<GrantedAuthority> getGrantedAuthority(int id){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<String> authorities = authorityRepository.findAuthoritiesById(id);
        for(String authority : authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
        return grantedAuthorities;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        //Tells which type of authentication provider it is.
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
