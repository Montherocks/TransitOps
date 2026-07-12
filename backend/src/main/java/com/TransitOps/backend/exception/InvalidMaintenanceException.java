package com.TransitOps.backend.exception;

public class InvalidMaintenanceException extends RuntimeException {

    public InvalidMaintenanceException(String message) {
        super(message);
    }

}