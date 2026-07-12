package com.TransitOps.backend.exception;

public class MaintenanceAlreadyCompletedException extends RuntimeException {

    public MaintenanceAlreadyCompletedException(String message) {
        super(message);
    }

}