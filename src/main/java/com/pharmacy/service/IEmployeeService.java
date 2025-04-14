package com.pharmacy.service;

import com.pharmacy.dto.EmployeeDto;
import com.pharmacy.dto.UpdateEmployeeDto;
import com.pharmacy.model.Employee;
import org.springframework.data.domain.Page;

public interface IEmployeeService {
    Page<Employee> findAll(Integer pageNumber, Integer numberOfElements, String sortDir, String sortBy);

    void addEmployee(EmployeeDto employeeDto);

    void updateEmployee(UpdateEmployeeDto employeeDto);

    void deleteEmployee(Long id);

    void deleteAllEmployees();

    void setActiveEmployee(Long employeeId, Boolean active);

    Employee findByUserName(String userName);

    void save(Employee employee);
    void lockEmployee(Long employeeId, Boolean lock);
}
