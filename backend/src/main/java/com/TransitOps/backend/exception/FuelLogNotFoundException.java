package com.TransitOps.backend.exception;

public class FuelLogNotFoundException extends RuntimeException {

    public FuelLogNotFoundException(String message){
        super(message);
    }

}