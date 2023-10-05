package com.example.api.exception;

public class InvalidPhoneNameException extends ApiException {
    public InvalidPhoneNameException(String message) {
        super(message);
    }
}
