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

        ContactDetail godContactDetail = ContactDetail.builder().mobile1("9766750554").mobileCountryCode("91").name("God").build();
        Payroll godPayroll = Payroll.builder().salary(100.0).joiningDate(LocalDate.now().minusMonths(10)).build();
        Address godAddress = Address.builder().line1("Heaven").line2("Heaven").city("Heaver").state("Heaven").country("Heaven").pincode("1").isDefault(true).contactDetail(ContactDetail.builder().name("A").mobile1("9766750554").mobileCountryCode("91").build()).build();
        Employee god = Employee.builder()
                .userName("god")
                .password(passwordEncoder.encode("god"))
                .roles(Arrays.asList(GOD))
                .firstName("God")
                .lastName("God")
                .contactDetail(godContactDetail)
                .dateOfBirth(LocalDate.now().minusYears(25))
                .personalDocumentNumber("ABC")
                .active(true)
                .accountLocked(false)
                .payroll(godPayroll)
                .address(godAddress)
                .build();

        ContactDetail adminContactDetail = ContactDetail.builder().mobile1("9766750554").mobileCountryCode("91").name("Admin").build();
        Payroll adminPayroll = Payroll.builder().salary(1000.0).joiningDate(LocalDate.now().minusYears(2)).build();
        Address adminAddress = Address.builder().line1("Admin").line2("Admin").city("Heaver").state("Admin").country("Admin").pincode("1").isDefault(true).contactDetail(ContactDetail.builder().name("B").mobile1("9766750554").mobileCountryCode("91").build()).build();
        Employee admin = Employee.builder()
                .userName("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(Arrays.asList(ADMIN))
                .firstName("Admin")
                .lastName("Admin")
                .contactDetail(adminContactDetail)
                .dateOfBirth(LocalDate.now().minusYears(20))
                .personalDocumentNumber("XYZ")
                .active(true)
                .accountLocked(false)
                .payroll(adminPayroll)
                .address(adminAddress)
                .build();

        ContactDetail managerContactDetail = ContactDetail.builder().mobile1("8080837481").mobileCountryCode("91").name("Manager").build();
        Payroll managerPayroll = Payroll.builder().salary(1000.0).joiningDate(LocalDate.now().minusYears(2)).build();
        Address managerAddress = Address.builder().line1("Manager").line2("Manager").city("Heaver").state("Manager").country("Manager").pincode("1").isDefault(true).contactDetail(ContactDetail.builder().name("C").mobile1("9766750554").mobileCountryCode("91").build()).build();
        Employee manager = Employee.builder()
                .userName("manager")
                .password(passwordEncoder.encode("manager"))
                .roles(Arrays.asList(MANAGER))
                .firstName("Manager")
                .lastName("Manager")
                .contactDetail(managerContactDetail)
                .dateOfBirth(LocalDate.now().minusYears(10))
                .personalDocumentNumber("LLL")
                .active(true)
                .accountLocked(false)
                .payroll(managerPayroll)
                .address(managerAddress)
                .build();

        ContactDetail pharmacistContactDetail = ContactDetail.builder().mobile1("8080837481").mobileCountryCode("91").name("Pharmacist").build();
        Payroll pharmacistPayroll = Payroll.builder().salary(1000.0).joiningDate(LocalDate.now().minusYears(4)).build();
        Address pharmacistAddress = Address.builder().line1("Pharmacist").line2("Pharmacist").city("Heaver").state("Pharmacist").country("Pharmacist").pincode("1").isDefault(true).contactDetail(ContactDetail.builder().name("D").mobile1("9766750554").mobileCountryCode("91").build()).build();
        Employee pharmacist = Employee.builder()
                .userName("pharmacist")
                .password(passwordEncoder.encode("pharmacist"))
                .roles(Arrays.asList(PHARMACIST))
                .firstName("Pharmacist")
                .lastName("Pharmacist")
                .contactDetail(pharmacistContactDetail)
                .dateOfBirth(LocalDate.now().minusYears(30))
                .personalDocumentNumber("PPP")
                .active(true)
                .accountLocked(false)
                .payroll(pharmacistPayroll)
                .address(pharmacistAddress)
                .build();

        ContactDetail cashierContactDetail = ContactDetail.builder().mobile1("8080837481").mobileCountryCode("91").name("Cashier").build();
        Payroll cashierPayroll = Payroll.builder().salary(1000.0).joiningDate(LocalDate.now().minusYears(4)).build();
        Employee cashier = Employee.builder()
                .userName("cashier")
                .password(passwordEncoder.encode("cashier"))
                .roles(Arrays.asList(CASHIER))
                .firstName("Cashier")
                .lastName("Cashier")
                .contactDetail(cashierContactDetail)
                .dateOfBirth(LocalDate.now().minusYears(20))
                .personalDocumentNumber("UUU")
                .active(true)
                .accountLocked(false)
                .payroll(cashierPayroll)
                .build();

        ContactDetail helperContactDetail = ContactDetail.builder().mobile1("8080837481").mobileCountryCode("91").name("Helper").build();
        Payroll helperPayroll = Payroll.builder().salary(1000.0).joiningDate(LocalDate.now().minusYears(7)).build();
        Employee helper = Employee.builder()
                .userName("helper")
                .password(passwordEncoder.encode("helper"))
                .roles(Arrays.asList(HELPER))
                .firstName("Helper")
                .lastName("Helper")
                .contactDetail(helperContactDetail)
                .dateOfBirth(LocalDate.now().minusYears(30))
                .personalDocumentNumber("GSS")
                .active(true)
                .accountLocked(false)
                .payroll(helperPayroll)
                .build();

        ContactDetail inactiveContactDetail = ContactDetail.builder().mobile1("8080837481").mobileCountryCode("91").name("Inactive").build();
        Payroll inactivePayroll = Payroll.builder().salary(1000.0).joiningDate(LocalDate.now().minusYears(7)).build();
        Employee inactive = Employee.builder()
                .userName("inactive")
                .password(passwordEncoder.encode("inactive"))
                .roles(Arrays.asList(ADMIN))
                .firstName("Inactive")
                .lastName("Inactive")
                .contactDetail(inactiveContactDetail)
                .dateOfBirth(LocalDate.now().minusYears(38))
                .personalDocumentNumber("ASP")
                .active(false)
                .accountLocked(false)
                .payroll(inactivePayroll)
                .build();

        ContactDetail lockedContactDetail = ContactDetail.builder().mobile1("8080837481").mobileCountryCode("91").name("Locked").build();
        Payroll lockedPayroll = Payroll.builder().salary(1000.0).joiningDate(LocalDate.now().minusYears(7)).build();
        Employee locked = Employee.builder()
                .userName("locked")
                .password(passwordEncoder.encode("locked"))
                .roles(Arrays.asList(PHARMACIST))
                .firstName("Locked")
                .lastName("Locked")
                .contactDetail(lockedContactDetail)
                .dateOfBirth(LocalDate.now().minusYears(38))
                .personalDocumentNumber("ASP")
                .active(true)
                .accountLocked(true)
                .payroll(lockedPayroll)
                .build();

        iEmployeeService.saveAll(Arrays.asList(god,admin,manager,pharmacist,cashier,helper,inactive,locked));

        bulkInsertProducts.insert();
    }
}
