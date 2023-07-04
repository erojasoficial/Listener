package com.example.demo.handler;

import com.example.demo.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.IndexOutOfBoundsException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EncryptionException.class)
    public ResponseEntity<String> handleEncryptionException(EncryptionException e) {
        return new ResponseEntity<>("Error in encryption/decryption: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WebConfigException.class)
    public ResponseEntity<String> handleWebConfigException(WebConfigException e) {
        return new ResponseEntity<>("Error in web configuration: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> handleIndexOutOfBoundsException(IndexOutOfBoundsException e) {
        return new ResponseEntity<>("Error in string indices: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StringConversionException.class)
    public ResponseEntity<String> handleStringConversionException(StringConversionException e) {
        return new ResponseEntity<>("Error in string conversion: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonProcessingRuntimeException.class)
    public ResponseEntity<String> handleJsonProcessingRuntimeException(JsonProcessingRuntimeException e) {
        return new ResponseEntity<>("Error processing JSON: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiCallRuntimeException.class)
    public ResponseEntity<String> handleApiCallRuntimeException(ApiCallRuntimeException e) {
        return new ResponseEntity<>("Error in API call: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpCallRuntimeException.class)
    public ResponseEntity<String> handleHttpCallRuntimeException(HttpCallRuntimeException e) {
        return new ResponseEntity<>("Error in HTTP request: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NetworkRuntimeException.class)
    public ResponseEntity<String> handleNetworkRuntimeException(NetworkRuntimeException e) {
        return new ResponseEntity<>("Failed to access the API: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageProcessingRuntimeException.class)
    public ResponseEntity<String> handleMessageProcessingRuntimeException(MessageProcessingRuntimeException e) {
        return new ResponseEntity<>("Error processing the message: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<String> handleMessageNotFoundException(MessageNotFoundException e) {
        return new ResponseEntity<>("Message not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ScheduleServiceException.class)
    public ResponseEntity<String> handleScheduleServiceException(ScheduleServiceException e) {
        return new ResponseEntity<>("Error in ScheduleService: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

