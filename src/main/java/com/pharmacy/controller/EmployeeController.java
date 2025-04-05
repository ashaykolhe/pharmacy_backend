package com.pharmacy.controller;

import com.pharmacy.constants.Constants;
import com.pharmacy.dto.EmployeeDto;
import com.pharmacy.model.Employee;
import com.pharmacy.service.IEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final IEmployeeService iEmployeeService;

    public EmployeeController(IEmployeeService iEmployeeService) {
        this.iEmployeeService = iEmployeeService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(iEmployeeService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employeeDto) {
        System.out.println(employeeDto);
        iEmployeeService.addEmployee(employeeDto);
        return ResponseEntity.ok(Constants.EMPLOYEE_ADDED);
    }

    @PutMapping("/setActive/{employeeId}")
    public ResponseEntity<String> setActiveEmployee(@PathVariable Long employeeId, @RequestParam Boolean active) {
        iEmployeeService.setActiveEmployee(employeeId, active);
        return ResponseEntity.ok(active ? Constants.EMPLOYEE_ACTIVATED : Constants.EMPLOYEE_DEACTIVATED);
    }
}
