package com.springcloud.shoppingsystem.exceptions;

public class ShoppingCardNotFoundException extends RuntimeException {
    public ShoppingCardNotFoundException(String message){
        super(message);
    }
}
