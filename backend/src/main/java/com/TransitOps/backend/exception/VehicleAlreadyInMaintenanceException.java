package com.TransitOps.backend.exception;

public class VehicleAlreadyInMaintenanceException extends RuntimeException {

    public VehicleAlreadyInMaintenanceException(String message) {
        super(message);
    }

}