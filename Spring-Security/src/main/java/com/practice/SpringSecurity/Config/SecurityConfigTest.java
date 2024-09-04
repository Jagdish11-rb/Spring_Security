package com.practice.SpringSecurity.Config;

import com.practice.SpringSecurity.Component.CustomPasswordChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("test")
public class SecurityConfigTest extends CustomPasswordChecker {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/test-account", "/test-balance", "/test-card", "test-loan")
                        .authenticated()
                        .requestMatchers("/test-contact", "/test-notice", "/create-user")
                        .permitAll()
                        .anyRequest().authenticated())

                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * Using InMemoryUserDetailsManager
     */
    /*@Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("user").password("{noop}user@123").authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$Am5DNZAJpmBmG2Wph8/MM.8azXl6C9aF3ScO6Q7mlHnyEje/EkVvq").authorities("write").build();
        return new InMemoryUserDetailsManager(user,admin);
    }*/

    /**
     * Using JdbcUserDetailsManager .
     */
    /*@Bean
    public UserDetailsService userDetailsService(DataSource datasource) {
        return new JdbcUserDetailsManager(datasource);
    }*/


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
