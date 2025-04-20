package com.pharmacy.dto;

import jakarta.validation.constraints.NotEmpty;

public class PasswordChangeDto {
    @NotEmpty
    private Long id;
    @NotEmpty
    private String password;
}
