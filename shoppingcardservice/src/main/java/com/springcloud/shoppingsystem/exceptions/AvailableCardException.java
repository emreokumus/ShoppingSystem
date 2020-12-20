package com.springcloud.shoppingsystem.exceptions;

public class AvailableCardException extends RuntimeException {
    public AvailableCardException(String message){
        super(message);
    }
}