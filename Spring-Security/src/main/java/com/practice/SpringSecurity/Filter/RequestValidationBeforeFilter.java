package com.practice.SpringSecurity.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class RequestValidationBeforeFilter implements Filter {

    public static final String AUTHENTICATION_SCHEME_BASIC="Basic";
    private final Charset credentialsCharset = StandardCharsets.UTF_8;

    private static final Logger logger = LogManager.getLogger(RequestValidationBeforeFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String header = httpServletRequest.getHeader(AUTHORIZATION);
        if(header!=null){
            header = header.trim();
            if(StringUtils.startsWithIgnoreCase(header,AUTHENTICATION_SCHEME_BASIC)){
                byte[] base64Token = header.substring(6).getBytes(credentialsCharset);
                byte[] decoded; String email;
                try{
                    decoded = Base64.getDecoder().decode(base64Token);
                    String token = new String(decoded,credentialsCharset);
                    int delim = token.indexOf(":");
                    if(delim==-1){
                        throw new BadCredentialsException("Invalid token");
                    }
                    email = token.substring(0,delim);
                }catch(IllegalArgumentException i){
                    throw new BadCredentialsException("Failed to decode authentication token.");
                }
                if(email.toLowerCase().contains("demo")){
                    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    logger.info("Can't access as your username contains some suspicious content <demo>.");
                }else{
                    filterChain.doFilter(servletRequest,servletResponse);
                }
            }
        }
    }
}
