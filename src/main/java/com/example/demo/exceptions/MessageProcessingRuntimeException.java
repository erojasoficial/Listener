package com.example.demo.exceptions;

public class MessageProcessingRuntimeException extends RuntimeException {
    public MessageProcessingRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}