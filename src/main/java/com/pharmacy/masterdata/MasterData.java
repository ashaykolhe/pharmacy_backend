package com.pharmacy.masterdata;

import com.pharmacy.constants.Constants;
import com.pharmacy.model.ContactDetail;
import com.pharmacy.model.Employee;
import com.pharmacy.model.Permission;
import com.pharmacy.model.Role;
import com.pharmacy.service.IEmployeeService;
import com.pharmacy.service.IPermissionService;
import com.pharmacy.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class MasterData {
    private final IRoleService iRoleService;
    private final IPermissionService iPermissionService;
    private final IEmployeeService iEmployeeService;
    private final PasswordEncoder passwordEncoder;

    public void masterData() {
        Permission ADD_EMPLOYEE = Permission.builder().name(Constants.PERMISSION.EMPLOYEE.ADD_EMPLOYEE).build();
        Permission UPDATE_EMPLOYEE = Permission.builder().name(Constants.PERMISSION.EMPLOYEE.UPDATE_EMPLOYEE).build();
        Permission DELETE_EMPLOYEE = Permission.builder().name(Constants.PERMISSION.EMPLOYEE.DELETE_EMPLOYEE).build();
        Permission ACTIVATE_DEACTIVATE_EMPLOYEE = Permission.builder().name(Constants.PERMISSION.EMPLOYEE.ACTIVATE_DEACTIVATE_EMPLOYEE).build();
        Permission LOCK_UNLOCK_EMPLOYEE = Permission.builder().name(Constants.PERMISSION.EMPLOYEE.LOCK_UNLOCK_EMPLOYEE).build();
        Permission CHANGE_EMPLOYEE_PASSWORD = Permission.builder().name(Constants.PERMISSION.EMPLOYEE.CHANGE_EMPLOYEE_PASSWORD).build();
        iPermissionService.savePermissions(Arrays.asList(ADD_EMPLOYEE, UPDATE_EMPLOYEE, DELETE_EMPLOYEE, ACTIVATE_DEACTIVATE_EMPLOYEE, LOCK_UNLOCK_EMPLOYEE, CHANGE_EMPLOYEE_PASSWORD));

        Role GOD = Role.builder().name(Constants.ROLE.GOD).permissions(Arrays.asList(ADD_EMPLOYEE
                , UPDATE_EMPLOYEE
                , DELETE_EMPLOYEE
                , ACTIVATE_DEACTIVATE_EMPLOYEE,
                LOCK_UNLOCK_EMPLOYEE,
                CHANGE_EMPLOYEE_PASSWORD)).build();
        Role ADMIN = Role.builder().name(Constants.ROLE.ADMIN).permissions(Arrays.asList(ADD_EMPLOYEE
                , UPDATE_EMPLOYEE
                , DELETE_EMPLOYEE
                , ACTIVATE_DEACTIVATE_EMPLOYEE,
                LOCK_UNLOCK_EMPLOYEE,
                CHANGE_EMPLOYEE_PASSWORD)).build();
        Role MANAGER = Role.builder().name(Constants.ROLE.MANAGER).permissions(Arrays.asList(ADD_EMPLOYEE
                , UPDATE_EMPLOYEE
                , DELETE_EMPLOYEE)).build();
        Role PHARMACIST = Role.builder().name(Constants.ROLE.PHARMACIST).build();
        Role CASHIER = Role.builder().name(Constants.ROLE.CASHIER).build();
        Role HELPER = Role.builder().name(Constants.ROLE.HELPER).build();
        iRoleService.saveRoles(Arrays.asList(GOD, ADMIN, MANAGER, PHARMACIST, HELPER, CASHIER));

        ContactDetail contactDetail = ContactDetail.builder().mobile1("9766750554").mobileCountryCode(91).build();
        Employee employee = Employee.builder()
                .userName("god")
                .password(passwordEncoder.encode("god"))
                .roles(Arrays.asList(GOD))
                .firstName("God")
                .lastName("God")
                .contactDetail(contactDetail)
                .dateOfBirth(LocalDate.now())
                .personalDocumentNumber("ABC")
                .active(true)
                .accountLocked(false)
                .build();

        iEmployeeService.save(employee);
    }
}
