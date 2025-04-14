package com.pharmacy.exception;

public class EmployeeAlreadyLockedException extends RuntimeException {
    public EmployeeAlreadyLockedException(String message) {
        super(message);
    }
}
