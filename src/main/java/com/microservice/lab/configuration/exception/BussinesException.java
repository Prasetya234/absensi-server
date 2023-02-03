package com.microservice.lab.configuration.exception;

public class BussinesException extends RuntimeException {
    public BussinesException(String message) {
        super(message);
    }
}
