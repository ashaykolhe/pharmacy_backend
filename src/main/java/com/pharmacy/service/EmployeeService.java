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
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Page<Employee> findAll(Integer pageNumber, Integer numberOfElements, String sortDir, String sortBy) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNumber, numberOfElements, sort);
        return employeeRepository.findAll(pageRequest);
    }

    @Override
    public void addEmployee(EmployeeDto employeeDto) {
        log.debug("employee dto " + employeeDto);
        employeeRepository.findByUserName(employeeDto.getUserName()).ifPresent(user -> {
            throw new UserNameAlreadyExistsException(user.getUserName() + " " + Constants.USERNAME_ALREADY_EXISTS);
        });
        Employee employee = employeeMapper.dtoToModel(employeeDto);
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(EmployeeDto employeeDto) {
        log.debug("employee dto " + employeeDto);
        employeeRepository.findById(employeeDto.getId()).ifPresentOrElse(employee -> {
            employeeMapper.updateEmployee(employeeDto, employee);
            employeeRepository.save(employee);
        }, () -> {
            throw new EmployeeNotFoundException(Constants.EMPLOYEE_NOT_FOUND);
        });
    }

    @Override
    public void deleteEmployee(Long id) {
        log.debug("delete " + id);
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

    @Override
    public Employee findByUserName(String userName) {
        log.debug("finding user name " + userName);
        return employeeRepository.findByUserName(userName).orElseThrow(() -> {
            throw new EmployeeNotFoundException(Constants.EMPLOYEE_NOT_FOUND);
        });
    }
}
