package com.pharmacy.dto;

import com.pharmacy.model.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class EmployeeDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private ContactDetail contactDetail;
    private Address address;
    private String password;
    private Payroll payroll;
    private LocalDate dateOfBirth;
    private String userName;
    private PersonalDocument document;
    private Photo photo;
    private List<Role> roles;
    private String pharmacyLicenseNumber;
    private Boolean active;
    private Boolean accountLocked;
}
