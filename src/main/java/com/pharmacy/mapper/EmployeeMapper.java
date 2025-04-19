package com.pharmacy.mapper;

import com.pharmacy.dto.EmployeeDto;
import com.pharmacy.dto.UpdateEmployeeDto;
import com.pharmacy.model.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee dtoToModel(EmployeeDto employeeDto);

    EmployeeDto modelToDto(Employee employee);

    /*
    * IGNORE
If a source bean property equals null the target bean property will be ignored and retain its existing value.
SET_TO_DEFAULT
If a source bean property equals null the target bean property will be set to its default value.
SET_TO_NULL
If a source bean property equals null the target bean property will be set explicitly to null.
    * */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
        //send all values of employee while updating
    void updateEmployee(UpdateEmployeeDto updateEmployeeDto, @MappingTarget Employee employee);
}
