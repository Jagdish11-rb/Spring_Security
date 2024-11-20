package com.practice.SpringSecurity.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.IOException;
import java.util.Base64;

public class RequestValidatorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null) {
            try{
                if (header.startsWith("Basic")) {
                    header = header.substring(6);
                    byte[] decodedBytes = Base64.getDecoder().decode(header);
                    header = new String(decodedBytes);
                    int delim = header.indexOf(":");
                    if (delim == -1) {
                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        res.getWriter().write("Invalid authorization token");
                        return;
                    }
                    String username = header.substring(0, delim);
                    if (username.contains("test")) {
                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        res.getWriter().write("Testing users not allowed.");
                        return;
                    }
                }
            }catch(Exception e){
                throw new BadCredentialsException("Unable to decode authorization header.");
            }
        } else {
            throw new BadCredentialsException("Authorization header is null.");
        }

        filterChain.doFilter(req, res);
    }

}
