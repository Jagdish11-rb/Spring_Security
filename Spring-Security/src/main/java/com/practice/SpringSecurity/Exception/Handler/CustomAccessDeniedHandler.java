package com.practice.SpringSecurity.Exception.Handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        LocalDateTime timestamp = LocalDateTime.now();
        String message = (accessDeniedException!=null && accessDeniedException.getMessage()!=null) ? accessDeniedException.getMessage() : "Unauthorized";
        response.setHeader("Spring security denied response", "Chala jaa bsdk..");
        response.setStatus(HttpStatus.FORBIDDEN.value());

        String responseJson = String.format(
                "{\"timestamp\":\"%s\",\"status\":\"%s\",\"error\":\"%s\",\"message\":\"%s\",\"path\":\"%s\"}",timestamp,HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN.getReasonPhrase(),message,request.getRequestURI()
        );

        response.getWriter().write(responseJson);
    }
}
