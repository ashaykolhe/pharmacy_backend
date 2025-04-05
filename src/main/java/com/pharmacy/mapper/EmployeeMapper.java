package com.pharmacy.mapper;

import com.pharmacy.dto.EmployeeDto;
import com.pharmacy.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee dtoToModel(EmployeeDto employeeDto);

    EmployeeDto modelToDto(Employee employee);
}
