package com.practice.SpringSecurity.Component;

import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordChecker implements CompromisedPasswordChecker {
    @Override
    public CompromisedPasswordDecision check(String password) {
        if(password.contains("123") || password.contains("321")){
            return new CompromisedPasswordDecision(false);
        }
        return new CompromisedPasswordDecision(true);
    }
}
