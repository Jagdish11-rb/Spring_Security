package com.practice.SpringSecurity.Exception.Handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * BasicAuthenticationEntryPoint.class ( Spring security default entry point to handle authentication exception )
 */

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        LocalDateTime timestamp = LocalDateTime.now();
        String message = (authException!=null && authException.getMessage()!=null) ? authException.getMessage() : "Unauthorized";
        response.setHeader("Spring security response", "Chal hatt bsdk..");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        String responseJson = String.format(
                "{\"timestamp\":\"%s\",\"status\":\"%s\",\"error\":\"%s\",\"message\":\"%s\",\"path\":\"%s\"}",timestamp,HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase(),message,request.getRequestURI()
        );

        response.getWriter().write(responseJson);
    }
}
