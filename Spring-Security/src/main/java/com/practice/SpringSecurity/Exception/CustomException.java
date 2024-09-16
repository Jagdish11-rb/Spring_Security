package com.practice.SpringSecurity.Exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{
    private String error;
    private String status;
    public CustomException(String error,String status){
        this.error=error;
        this.status=status;
    }

    public CustomException(String error){
        this.error=error;
    }


}
