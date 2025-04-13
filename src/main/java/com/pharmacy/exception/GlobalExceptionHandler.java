package com.pharmacy.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> employeeNotFoundExceptionHandler(Exception exception) {
        return genericHandler(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<?> userNameAlreadyExistsExceptionHandler(Exception exception) {
        return genericHandler(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMostSpecificCause().getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EmployeeAlreadyActiveException.class, EmployeeAlreadyDeactiveException.class})
    public ResponseEntity<?> employeeActiveDeactiveExceptionHandler(Exception exception) {
        return genericHandler(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> genericHandler(Exception exception, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage(), null), httpStatus);
    }
}
