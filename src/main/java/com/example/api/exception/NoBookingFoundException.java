package com.example.api.exception;

public class NoBookingFoundException extends ApiException {
    public NoBookingFoundException(String message) {
        super(message);
    }
}
