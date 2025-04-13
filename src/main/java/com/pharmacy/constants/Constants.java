package com.pharmacy.constants;

public interface Constants {
    static interface EMPLOYEE {
        String EMPLOYEE_ADDED = "Employee added";
        String EMPLOYEE_UPDATED = "Employee updated";
        String EMPLOYEE_DELETED = "Employee deleted";
        String ALL_EMPLOYEES_DELETED = "All Employees deleted";
        String EMPLOYEE_NOT_FOUND = "Employee not found";
        String EMPLOYEE_ACTIVATED = "Employee activated";
        String EMPLOYEE_DEACTIVATED = "Employee deactivated";
        String USERNAME_ALREADY_EXISTS = "username already exists";
        String EMPLOYEE_ALREADY_ACTIVE = "Employee already active";
        String EMPLOYEE_ALREADY_DEACTIVE = "Employee already deactive";
    }
    static interface ROLE_PERMISSION {
        String ADMIN_ADD_EMPLOYEE = "ADMIN_ADD_EMPLOYEE";
    }

    static interface GENERAL {
        String TOKEN_DELETED_SUCCESSFULLY = "TOKEN_DELETED_SUCCESSFULLY";
    }
}
