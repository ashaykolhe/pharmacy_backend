package com.pharmacy.service;

import com.pharmacy.constants.Constants;
import com.pharmacy.dto.EmployeeDto;
import com.pharmacy.exception.EmployeeNotFoundException;
import com.pharmacy.mapper.EmployeeMapper;
import com.pharmacy.model.Employee;
import com.pharmacy.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void addEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.dtoToModel(employeeDto);
        log.debug("employee dto " + employeeDto);
        log.debug("employee " + employee);
        employeeRepository.save(employee);
    }

    @Override
    public void setActiveEmployee(Long employeeId, Boolean active) {
        log.debug("employee id " + employeeId + " active " + active);
        Optional<Employee> byId = employeeRepository.findById(employeeId);
        byId.orElseThrow(() -> new EmployeeNotFoundException(Constants.EMPLOYEE_NOT_FOUND)).setActive(active);
        employeeRepository.save(byId.get());
    }
}
