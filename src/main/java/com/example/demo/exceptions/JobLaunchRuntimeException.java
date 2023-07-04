package com.example.demo.exceptions;

public class JobLaunchRuntimeException extends RuntimeException {
    public JobLaunchRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
