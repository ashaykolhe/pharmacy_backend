package com.pharmacy.exception;

import com.pharmacy.constants.Constants;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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

    @ExceptionHandler({EmployeeAlreadyActiveException.class, EmployeeAlreadyDeactiveException.class, EmployeeAlreadyLockedException.class, EmployeeAlreadyNotLockedException.class})
    public ResponseEntity<?> employeeActiveDeactiveLockNotlockExceptionHandler(Exception exception) {
        return genericHandler(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> genericHandler(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), message, null), httpStatus);
    }

    @ExceptionHandler({DisabledException.class, LockedException.class})
    public ResponseEntity<?> disabledLockedException(Exception exception) {
        return genericHandler(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadCredentialsException.class, InternalAuthenticationServiceException.class})
    public ResponseEntity<?> badcredentialsException(Exception exception) {
        return genericHandler(Constants.GENERAL.BAD_CREDENTIALS, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(Exception exception) {
        return genericHandler(Constants.GENERAL.ACCESS_DENIED, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ExpiredJwtException.class, MalformedJwtException.class})
    public ResponseEntity<?> expiredOrMalformedJwtException(Exception exception) {
        return genericHandler(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
