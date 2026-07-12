package com.TransitOps.backend.exception;

public class MaintenanceNotFoundException extends RuntimeException {

    public MaintenanceNotFoundException(String message) {
        super(message);
    }

}