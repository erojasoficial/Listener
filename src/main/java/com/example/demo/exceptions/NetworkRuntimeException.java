package com.example.demo.exceptions;

public class NetworkRuntimeException extends RuntimeException {
    public NetworkRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}