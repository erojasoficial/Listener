package com.example.demo.exceptions;

public class ScheduleServiceException extends RuntimeException {
    public ScheduleServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}