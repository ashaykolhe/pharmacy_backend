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
        String EMPLOYEE_ALREADY_LOCKED = "Employee already locked";
        String EMPLOYEE_ALREADY_NOT_LOCKED = "Employee already not locked";
        String EMPLOYEE_LOCKED = "Employee locked";
        String EMPLOYEE_UNLOCKED = "Employee unlocked";
    }

    static interface GENERAL {
        String BAD_CREDENTIALS = "Username or Password is wrong.";
        String ACCESS_DENIED = "Access denied.";
    }

    static interface ROLE {
        String ADMIN = "ADMIN";
        String GOD = "GOD";
        String MANAGER = "MANAGER";
        String HELPER = "HELPER";
        String PHARMACIST = "PHARMACIST";
        String CASHIER = "CASHIER";
    }
    static interface PERMISSION {
        static interface EMPLOYEE {
            String ADD_EMPLOYEE = "ADD_EMPLOYEE";
            String UPDATE_EMPLOYEE = "UPDATE_EMPLOYEE";
            String DELETE_EMPLOYEE = "DELETE_EMPLOYEE";
            String ACTIVATE_DEACTIVATE_EMPLOYEE = "ACTIVATE_DEACTIVATE_EMPLOYEE";
            String LOCK_UNLOCK_EMPLOYEE = "LOCK_UNLOCK_EMPLOYEE";
            String CHANGE_EMPLOYEE_PASSWORD = "CHANGE_EMPLOYEE_PASSWORD";
        }
    }
}
