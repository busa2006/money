package com.example.demo.exception;

public class CMoneyNotFoundException extends RuntimeException {

    public CMoneyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CMoneyNotFoundException(String message) {
        super(message);
    }

    public CMoneyNotFoundException() {
        super();
    }
}
