package com.pharmacy.controller;

import com.pharmacy.constants.Constants;
import com.pharmacy.dto.EmployeeDto;
import com.pharmacy.model.Employee;
import com.pharmacy.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {
    private final IEmployeeService iEmployeeService;

    @GetMapping("/v1/findAll")
    public ResponseEntity<Page<Employee>> findAll(@RequestParam Integer pageNumber, @RequestParam Integer numberOfElements, @RequestParam String sortDir, @RequestParam String sortBy) {
        return ResponseEntity.ok(iEmployeeService.findAll(pageNumber, numberOfElements, sortDir, sortBy));
    }

    @PostMapping("/v1/add")
    public ResponseEntity<String> add(@RequestBody EmployeeDto employeeDto) {
        iEmployeeService.addEmployee(employeeDto);
        return ResponseEntity.ok(Constants.EMPLOYEE.EMPLOYEE_ADDED);
    }

    @PutMapping("/v1/update")
    public ResponseEntity<String> update(@RequestBody EmployeeDto employeeDto) {
        iEmployeeService.updateEmployee(employeeDto);
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

    @GetMapping("/v1/findByUserName/{userName}")
    public ResponseEntity<Employee> findByUserName(@PathVariable String userName) {
        return ResponseEntity.ok(iEmployeeService.findByUserName(userName));
    }

}
