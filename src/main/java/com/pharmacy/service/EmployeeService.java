package com.pharmacy.service;

import com.pharmacy.constants.Constants;
import com.pharmacy.dto.EmployeeDto;
import com.pharmacy.exception.EmployeeAlreadyActiveException;
import com.pharmacy.exception.EmployeeAlreadyDeactiveException;
import com.pharmacy.exception.EmployeeNotFoundException;
import com.pharmacy.exception.UserNameAlreadyExistsException;
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
        employeeRepository.findByUserName(employeeDto.getUserName()).ifPresent(user -> {
            throw new UserNameAlreadyExistsException(user.getUserName() + " " + Constants.USERNAME_ALREADY_EXISTS);
        });
        Employee employee = employeeMapper.dtoToModel(employeeDto);
        log.debug("employee dto " + employeeDto);
        log.debug("employee " + employee);
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(EmployeeDto employeeDto) {
        employeeRepository.findById(employeeDto.getId()).ifPresentOrElse(employee -> {
            employeeMapper.updateEmployee(employeeDto, employee);
            log.debug("employee dto " + employeeDto);
            log.debug("employee " + employee);
            employeeRepository.save(employee);
        }, () -> {
            throw new EmployeeNotFoundException(Constants.EMPLOYEE_NOT_FOUND);
        });
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> byId = employeeRepository.findById(id);
        employeeRepository.delete(byId.orElseThrow(() -> new EmployeeNotFoundException(Constants.EMPLOYEE_NOT_FOUND)));
    }

    @Override
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    @Override
    public void setActiveEmployee(Long employeeId, Boolean active) {
        log.debug("employee id " + employeeId + " active " + active);
        employeeRepository.findById(employeeId).ifPresentOrElse(employee -> {
            if (active.equals(employee.getActive())) {
                if (active)
                    throw new EmployeeAlreadyActiveException(Constants.EMPLOYEE_ALREADY_ACTIVE);
                else
                    throw new EmployeeAlreadyDeactiveException(Constants.EMPLOYEE_ALREADY_DEACTIVE);
            }
            employee.setActive(active);
            employeeRepository.save(employee);
        }, () -> {
            throw new EmployeeNotFoundException(Constants.EMPLOYEE_NOT_FOUND);
        });
    }
}
