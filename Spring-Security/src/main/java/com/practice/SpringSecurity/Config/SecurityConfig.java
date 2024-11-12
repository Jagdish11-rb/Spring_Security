package com.practice.SpringSecurity.Config;

import com.practice.SpringSecurity.Component.CustomPasswordChecker;
import com.practice.SpringSecurity.Exception.Handler.CustomAccessDeniedHandler;
import com.practice.SpringSecurity.Exception.Handler.CustomAuthenticationEntryPoint;
import com.practice.SpringSecurity.Filter.CsrfTokenFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@Profile("!test")
public class SecurityConfig extends CustomPasswordChecker {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .securityContext(contextConfig->contextConfig.requireExplicitSave(false))
                .sessionManagement(smc->smc.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
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
                .addFilterAfter(new CsrfTokenFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/test-account", "/get-customer-details","/csrf-testing").authenticated()
                        .requestMatchers("/test-notice", "/create-user").permitAll()
                        .anyRequest().authenticated());

        configureCsrf(http);

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
//        http.exceptionHandling(ex -> ex.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
        http.exceptionHandling(ex -> ex.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }

    public void configureCsrf(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        http
                .csrf(csrf->csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers("/create-user")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
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
