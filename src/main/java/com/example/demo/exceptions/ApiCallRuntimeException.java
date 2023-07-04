package com.example.demo.exceptions;

public class ApiCallRuntimeException extends RuntimeException {
    public ApiCallRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}