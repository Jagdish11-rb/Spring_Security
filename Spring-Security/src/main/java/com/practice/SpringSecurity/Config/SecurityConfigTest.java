package com.practice.SpringSecurity.Config;

import com.practice.SpringSecurity.Component.CustomPasswordChecker;
import com.practice.SpringSecurity.Exception.Handler.CustomAccessDeniedHandler;
import com.practice.SpringSecurity.Exception.Handler.CustomAuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@Profile("test")
public class SecurityConfigTest extends CustomPasswordChecker {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cc->cc.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
                        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                        corsConfiguration.setAllowCredentials(true);
                        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                        corsConfiguration.setMaxAge(3600l);
                        return corsConfiguration;
                    }
                }))

                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(smc->smc.sessionFixation(sfc->sfc.none())
                        .invalidSessionUrl("/invalidSession").maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/expired"))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/test-account","/get-customer-details").authenticated()
                        .requestMatchers( "/create-user","/test-notice","/invalidSession","/expired").permitAll()
                        .anyRequest().denyAll());

                http.formLogin(Customizer.withDefaults());

                http.httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
                http.exceptionHandling(exc -> exc.accessDeniedHandler(new CustomAccessDeniedHandler()));
                /*By enabling below , it catches all the authentication exception of application . But the catch is , http default login page will not be displayed while testing through browser as it will throw the exception before it.*/
//                http.exceptionHandling(ex -> ex.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

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
