package com.pharmacy.mapper;

import com.pharmacy.dto.EmployeeDto;
import com.pharmacy.model.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee dtoToModel(EmployeeDto employeeDto);

    EmployeeDto modelToDto(Employee employee);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEmployee(EmployeeDto employeeDto, @MappingTarget Employee employee);
}
