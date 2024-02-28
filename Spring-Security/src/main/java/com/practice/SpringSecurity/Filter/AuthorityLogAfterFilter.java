package com.practice.SpringSecurity.Filter;

import jakarta.servlet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.io.IOException;
public class AuthorityLogAfterFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(AuthorityLogAfterFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            if(authentication.isAuthenticated()){
                logger.info("An user with email : {} and authorities : {} reached us.",authentication.getName(),authentication.getAuthorities());
            }
        }else{
            logger.info("Authentication is null.");
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

}
