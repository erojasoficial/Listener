package com.example.demo.exceptions;

public class MessageSaveRuntimeException extends RuntimeException {
    public MessageSaveRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}