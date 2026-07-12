package com.TransitOps.backend.exception;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(String message) {
        super(message);
    }

}