package com.practice.SpringSecurity.Exception;

import com.practice.SpringSecurity.Utils.SecUtils;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class SecurityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        if(ex instanceof ValidationException){
            ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(),SecUtils.STATUS_FAILED);
            return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        }
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),SecUtils.STATUS_FAILED);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),SecUtils.STATUS_FAILED);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getError(),ex.getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}

