package com.pharmacy.exception;

public class EmployeeAlreadyDeactiveException extends RuntimeException{
    public EmployeeAlreadyDeactiveException(String message) {
        super(message);
    }
}
