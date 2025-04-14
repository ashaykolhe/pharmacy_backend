package com.pharmacy.exception;

public class EmployeeAlreadyNotLockedException extends RuntimeException{
    public EmployeeAlreadyNotLockedException(String message) {
        super(message);
    }
}
