package com.pharmacy.controller;

import com.pharmacy.aop.NoActivityLog;
import com.pharmacy.constants.Constants;
import com.pharmacy.dto.EmployeeDto;
import com.pharmacy.dto.UpdateEmployeeDto;
import com.pharmacy.exception.EmployeeNotFoundException;
import com.pharmacy.model.Employee;
import com.pharmacy.service.IEmployeeService;
import com.pharmacy.utils.Utils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {
    private final IEmployeeService iEmployeeService;

    @NoActivityLog
    @GetMapping("/v1/findAll")
    public ResponseEntity<Page<Employee>> findAll(@RequestParam Integer pageNumber, @RequestParam Integer numberOfElements, @RequestParam String sortDir, @RequestParam String sortBy) {
        return ResponseEntity.ok(iEmployeeService.findAll(pageNumber, numberOfElements, sortDir, sortBy));
    }

    @PostMapping("/v1/add")
    public ResponseEntity<?> add(@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(Utils.validationErrors(bindingResult));
        }
        iEmployeeService.addEmployee(employeeDto);
        return ResponseEntity.ok(Constants.EMPLOYEE.EMPLOYEE_ADDED);
    }

    @PutMapping("/v1/update")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateEmployeeDto updateEmployeeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Utils.validationErrors(bindingResult));
        }
        iEmployeeService.updateEmployee(updateEmployeeDto);
        return ResponseEntity.ok(Constants.EMPLOYEE.EMPLOYEE_UPDATED);
    }

    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        iEmployeeService.deleteEmployee(id);
        return ResponseEntity.ok(Constants.EMPLOYEE.EMPLOYEE_DELETED);
    }

    @DeleteMapping("/v1/delete")
    public ResponseEntity<String> deleteAll() {
        iEmployeeService.deleteAllEmployees();
        return ResponseEntity.ok(Constants.EMPLOYEE.ALL_EMPLOYEES_DELETED);
    }

    @PutMapping("/v1/active/{employeeId}")
    public ResponseEntity<String> setActive(@PathVariable Long employeeId, @RequestParam Boolean active) {
        iEmployeeService.setActiveEmployee(employeeId, active);
        return ResponseEntity.ok(active ? Constants.EMPLOYEE.EMPLOYEE_ACTIVATED : Constants.EMPLOYEE.EMPLOYEE_DEACTIVATED);
    }

    @NoActivityLog
    @GetMapping("/v1/findByUserName/{userName}")
    public ResponseEntity<Employee> findByUserName(@PathVariable String userName) {
        Employee employee = iEmployeeService.findByUserName(userName);
        if (employee == null) {
            throw new EmployeeNotFoundException(Constants.EMPLOYEE.EMPLOYEE_NOT_FOUND);
        }
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/v1/lock/{employeeId}")
    public ResponseEntity<String> setLock(@PathVariable Long employeeId, @RequestParam Boolean lock) {
        iEmployeeService.lockEmployee(employeeId, lock);
        return ResponseEntity.ok(lock ? Constants.EMPLOYEE.EMPLOYEE_LOCKED : Constants.EMPLOYEE.EMPLOYEE_UNLOCKED);
    }
}
