package com.example.demo.exception;

public class CCategoryNotFoundException extends RuntimeException {

    public CCategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CCategoryNotFoundException(String message) {
        super(message);
    }

    public CCategoryNotFoundException() {
        super();
    }
}
