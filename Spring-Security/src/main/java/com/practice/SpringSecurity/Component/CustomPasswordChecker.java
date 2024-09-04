package com.practice.SpringSecurity.Component;

import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class CustomPasswordChecker implements CompromisedPasswordChecker {

    private static final List<String> FAILURE_STRINGS = new ArrayList<>(Arrays.asList("123", "321", "1234"));

    @Override
    public CompromisedPasswordDecision check(String password) {
        AtomicBoolean compromisedPasswordDecision = new AtomicBoolean(false);
        FAILURE_STRINGS.parallelStream().forEach(str -> {
            if (password.contains(str)) {
                compromisedPasswordDecision.set(true);
            }
        });

        return new CompromisedPasswordDecision(compromisedPasswordDecision.get());
    }
}
