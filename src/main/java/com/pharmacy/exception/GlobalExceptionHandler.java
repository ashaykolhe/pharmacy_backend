package com.pharmacy.exception;

import com.pharmacy.constants.Constants;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> employeeNotFoundExceptionHandler(Exception exception) {
        return genericHandler(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<?> userNameAlreadyExistsExceptionHandler(Exception exception) {
        return genericHandler(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMostSpecificCause().getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EmployeeAlreadyActiveException.class, EmployeeAlreadyDeactiveException.class})
    public ResponseEntity<?> employeeActiveDeactiveExceptionHandler(Exception exception) {
        return genericHandler(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> genericHandler(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), message, null), httpStatus);
    }

    @ExceptionHandler({DisabledException.class, LockedException.class})
    public ResponseEntity<?> disabledLockedException(Exception exception) {
        return genericHandler(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<?> badcredentialsException(Exception exception) {
        return genericHandler(Constants.GENERAL.BAD_CREDENTIALS, HttpStatus.BAD_REQUEST);
    }
}
