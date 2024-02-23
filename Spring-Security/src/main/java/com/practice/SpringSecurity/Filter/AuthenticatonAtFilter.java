package com.practice.SpringSecurity.Filter;

import jakarta.servlet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class AuthenticatonAtFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(AuthenticatonAtFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Authentication is in progress. Please wait some time.");
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
