package com.springcloud.shoppingsystem.exceptions;

public class AvailableProductNotFoundException extends RuntimeException {
    public AvailableProductNotFoundException(String message){
        super(message);
    }
}