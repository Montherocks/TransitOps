package com.TransitOps.backend.exception;

public class InvalidExpenseException extends RuntimeException{

    public InvalidExpenseException(String message){

        super(message);

    }

}