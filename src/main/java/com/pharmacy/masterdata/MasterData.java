package com.pharmacy.masterdata;

import com.pharmacy.bulk.BulkInsertProducts;
import com.pharmacy.constants.Constants;
import com.pharmacy.model.*;
import com.pharmacy.service.IEmployeeService;
import com.pharmacy.service.IPermissionService;
import com.pharmacy.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class MasterData {
    private final IRoleService iRoleService;
    private final IPermissionService iPermissionService;
    private final IEmployeeService iEmployeeService;
    private final PasswordEncoder passwordEncoder;
    private final BulkInsertProducts bulkInsertProducts;

    public void store(MultipartFile file) {
        try {
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get("C:\\pharmacy\\pharmacy_backend\\database\\uploadedFile.xlsx"),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

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

        ContactDetail contactDetail = ContactDetail.builder().mobile1("9766750554").mobileCountryCode("91").name("Ashay Kolhe").build();
        Payroll payroll = Payroll.builder().salary(100.0).joiningDate(LocalDate.now()).build();
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
                .payroll(payroll)
                .build();

        iEmployeeService.save(employee);

        bulkInsertProducts.insert();
    }
}
