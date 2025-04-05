package com.pharmacy.service;

import com.pharmacy.dto.EmployeeDto;
import com.pharmacy.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    void addEmployee(EmployeeDto employeeDto);
    void setActiveEmployee(Long employeeId, Boolean active);
}
