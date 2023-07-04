package com.example.demo.exceptions;

public class StringConversionException extends RuntimeException {
    public StringConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}