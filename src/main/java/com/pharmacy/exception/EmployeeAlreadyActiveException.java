package com.pharmacy.exception;

public class EmployeeAlreadyActiveException extends RuntimeException {
    public EmployeeAlreadyActiveException(String message) {
        super(message);
    }
}
