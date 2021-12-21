package com.example.demo.exception;

public class CMoneyHistoryNotFoundException extends RuntimeException {

    public CMoneyHistoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CMoneyHistoryNotFoundException(String message) {
        super(message);
    }

    public CMoneyHistoryNotFoundException() {
        super();
    }
}
