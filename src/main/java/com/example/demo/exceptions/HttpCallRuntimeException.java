package com.example.demo.exceptions;

public class HttpCallRuntimeException extends RuntimeException {
    public HttpCallRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
