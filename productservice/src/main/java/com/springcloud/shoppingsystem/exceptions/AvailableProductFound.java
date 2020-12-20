package com.springcloud.shoppingsystem.exceptions;

public class AvailableProductFound extends RuntimeException {
    public AvailableProductFound(String message) {
        super(message);
    }
}