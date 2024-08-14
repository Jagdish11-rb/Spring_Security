package com.practice.SpringSecurity.Config;

import com.practice.SpringSecurity.Component.CustomPasswordChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.*;

import java.net.http.HttpClient;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                .requestMatchers("/test-account","/test-balance","/test-card","test-loan","/test-contact","/test-notice")
                .authenticated()
                .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("user").password("{noop}user").authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$Am5DNZAJpmBmG2Wph8/MM.8azXl6C9aF3ScO6Q7mlHnyEje/EkVvq").authorities("write").build();
        return new InMemoryUserDetailsManager(user,admin);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new CustomPasswordChecker();
    }


}
