package com.pharmacy.utils;

import com.pharmacy.exception.ErrorDetails;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static ErrorDetails validationErrors(BindingResult bindingResult) {
        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            errors.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
        });
        return new ErrorDetails(LocalDateTime.now(), "validation errors", errors);
    }
}
