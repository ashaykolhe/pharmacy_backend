package com.pharmacy.dto;

import com.pharmacy.model.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UpdateEmployeeDto {
    @NotNull
    private Long id;
    @NotEmpty
    private String firstName;
    private String middleName;
    @NotEmpty
    private String lastName;
    @NotNull
    private ContactDetail contactDetail;
    private Address address;
    private Payroll payroll;
    @NotNull
    private LocalDate dateOfBirth;
    private PersonalDocument document;
    private Photo photo;
    @NotNull
    private List<Role> roles;
    private String pharmacyLicenseNumber;
    private Boolean active;
    private Boolean accountLocked;
    @NotEmpty
    private String personalDocumentNumber;
}
