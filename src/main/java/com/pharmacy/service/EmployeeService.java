package com.pharmacy.service;

import com.pharmacy.constants.Constants;
import com.pharmacy.dto.EmployeeDto;
import com.pharmacy.dto.UpdateEmployeeDto;
import com.pharmacy.exception.EmployeeAlreadyActiveException;
import com.pharmacy.exception.EmployeeAlreadyDeactiveException;
import com.pharmacy.exception.EmployeeNotFoundException;
import com.pharmacy.exception.UserNameAlreadyExistsException;
import com.pharmacy.mapper.EmployeeMapper;
import com.pharmacy.model.Employee;
import com.pharmacy.repository.EmployeeRepository;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
public class EmployeeService implements IEmployeeService, UserDetailsService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<Employee> findAll(Integer pageNumber, Integer numberOfElements, String sortDir, String sortBy) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNumber, numberOfElements, sort);
        return employeeRepository.findAll(pageRequest);
    }

    @PreAuthorize("hasAuthority('" + Constants.ROLE.GOD + "_" + Constants.PERMISSION.EMPLOYEE.ADD_EMPLOYEE + "') OR hasAuthority('" + Constants.ROLE.ADMIN + "_" + Constants.PERMISSION.EMPLOYEE.ADD_EMPLOYEE + "') OR hasAuthority('" + Constants.ROLE.MANAGER + "_" + Constants.PERMISSION.EMPLOYEE.ADD_EMPLOYEE + "')")
    @Override
    public void addEmployee(EmployeeDto employeeDto) {
        log.debug("employee dto " + employeeDto);
        employeeRepository.findByUserName(employeeDto.getUserName()).ifPresent(user -> {
            throw new UserNameAlreadyExistsException(user.getUserName() + " " + Constants.EMPLOYEE.USERNAME_ALREADY_EXISTS);
        });
        Employee employee = employeeMapper.dtoToModel(employeeDto);
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        employeeRepository.save(employee);
    }

    @PreAuthorize("hasAuthority('" + Constants.ROLE.GOD + "_" + Constants.PERMISSION.EMPLOYEE.UPDATE_EMPLOYEE + "') OR hasAuthority('" + Constants.ROLE.ADMIN + "_" + Constants.PERMISSION.EMPLOYEE.UPDATE_EMPLOYEE + "') OR hasAuthority('" + Constants.ROLE.MANAGER + "_" + Constants.PERMISSION.EMPLOYEE.UPDATE_EMPLOYEE + "')")
    @Override
    public void updateEmployee(UpdateEmployeeDto updateEmployeeDto) {
        log.debug("update employeeDto " + updateEmployeeDto);
//        employeeRepository.existsById(employeeDto.getId())
        employeeRepository.findById(updateEmployeeDto.getId()).ifPresentOrElse(employee -> {
            employeeMapper.updateEmployee(updateEmployeeDto, employee);
            employeeRepository.save(employee);
        }, () -> {
            throw new EmployeeNotFoundException(Constants.EMPLOYEE.EMPLOYEE_NOT_FOUND);
        });
    }

    @PreAuthorize("hasAuthority('" + Constants.ROLE.GOD + "_" + Constants.PERMISSION.EMPLOYEE.DELETE_EMPLOYEE + "') OR hasAuthority('" + Constants.ROLE.ADMIN + "_" + Constants.PERMISSION.EMPLOYEE.DELETE_EMPLOYEE + "') OR hasAuthority('" + Constants.ROLE.MANAGER + "_" + Constants.PERMISSION.EMPLOYEE.DELETE_EMPLOYEE + "')")
    @Override
    public void deleteEmployee(Long id) {
        log.debug("delete " + id);
        Optional<Employee> byId = employeeRepository.findById(id);
        employeeRepository.delete(byId.orElseThrow(() -> new EmployeeNotFoundException(Constants.EMPLOYEE.EMPLOYEE_NOT_FOUND)));
    }

    @PreAuthorize("hasAuthority('" + Constants.ROLE.GOD + "_" + Constants.PERMISSION.EMPLOYEE.DELETE_EMPLOYEE + "')")
    @Override
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    @PreAuthorize("hasAuthority('" + Constants.ROLE.GOD + "_" + Constants.PERMISSION.EMPLOYEE.ACTIVATE_DEACTIVATE_EMPLOYEE + "') OR hasAuthority('" + Constants.ROLE.ADMIN + "_" + Constants.PERMISSION.EMPLOYEE.ACTIVATE_DEACTIVATE_EMPLOYEE + "')")
    @Override
    public void setActiveEmployee(Long employeeId, Boolean active) {
        log.debug("employee id " + employeeId + " active " + active);
        employeeRepository.findById(employeeId).ifPresentOrElse(employee -> {
            if (active.equals(employee.getActive())) {
                if (active)
                    throw new EmployeeAlreadyActiveException(Constants.EMPLOYEE.EMPLOYEE_ALREADY_ACTIVE);
                else
                    throw new EmployeeAlreadyDeactiveException(Constants.EMPLOYEE.EMPLOYEE_ALREADY_DEACTIVE);
            }
            employee.setActive(active);
            employeeRepository.save(employee);
        }, () -> {
            throw new EmployeeNotFoundException(Constants.EMPLOYEE.EMPLOYEE_NOT_FOUND);
        });
    }

    @Override
    public Employee findByUserName(String userName) {
        log.debug("finding user name " + userName);
        return employeeRepository.findByUserName(userName).orElseThrow(() -> {
            throw new EmployeeNotFoundException(Constants.EMPLOYEE.EMPLOYEE_NOT_FOUND);
        });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = findByUserName(username);
        return new CustomEmployeeDetails(employee);
    }

//    @PreAuthorize("hasAuthority('" + Constants.ROLE.GOD + "_" + Constants.PERMISSION.EMPLOYEE.ADD_EMPLOYEE + "') OR hasAuthority('" + Constants.ROLE.ADMIN + "_" + Constants.PERMISSION.EMPLOYEE.ADD_EMPLOYEE + "') OR hasAuthority('" + Constants.ROLE.MANAGER + "_" + Constants.PERMISSION.EMPLOYEE.ADD_EMPLOYEE + "')")
    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void lockEmployee(Employee employee) {
        employee.setAccountLocked(true);
        employeeRepository.save(employee);
    }
}
