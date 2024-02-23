package com.practice.SpringSecurity.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class GenericFilter extends GenericFilterBean {

    private static final Logger logger = LogManager.getLogger(GenericFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Testing a filter extending GenericFilterBean");
        logger.info("Security context details : {}",SecurityContextHolder.getContext());

        filterChain.doFilter(servletRequest,servletResponse);
    }


}
