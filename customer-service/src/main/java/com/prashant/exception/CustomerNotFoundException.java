package com.prashant.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String msg){
        super(msg);
    }
}
