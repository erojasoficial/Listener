package com.example.demo.exceptions;

public class IndexOutOfBoundsException extends RuntimeException {
    public IndexOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }
}