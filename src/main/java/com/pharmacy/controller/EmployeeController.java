package com.pharmacy.controller;

import com.pharmacy.constants.Constants;
import com.pharmacy.dto.EmployeeDto;
import com.pharmacy.model.Employee;
import com.pharmacy.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    private final IEmployeeService iEmployeeService;

    public EmployeeController(IEmployeeService iEmployeeService) {
        this.iEmployeeService = iEmployeeService;
    }

    @GetMapping("/findAllEmployees")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(iEmployeeService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employeeDto) {
        log.debug("employee dto " + employeeDto);
        iEmployeeService.addEmployee(employeeDto);
        return ResponseEntity.ok(Constants.EMPLOYEE_ADDED);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        log.debug("employee dto " + employeeDto);
        iEmployeeService.updateEmployee(employeeDto);
        return ResponseEntity.ok(Constants.EMPLOYEE_UPDATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        iEmployeeService.deleteEmployee(id);
        return ResponseEntity.ok(Constants.EMPLOYEE_DELETED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllEmployees() {
        iEmployeeService.deleteAllEmployees();
        return ResponseEntity.ok(Constants.ALL_EMPLOYEES_DELETED);
    }

    @PutMapping("/active/{employeeId}")
    public ResponseEntity<String> setActiveEmployee(@PathVariable Long employeeId, @RequestParam Boolean active) {
        log.debug("employee id " + employeeId + " active " + active);
        iEmployeeService.setActiveEmployee(employeeId, active);
        return ResponseEntity.ok(active ? Constants.EMPLOYEE_ACTIVATED : Constants.EMPLOYEE_DEACTIVATED);
    }
}
