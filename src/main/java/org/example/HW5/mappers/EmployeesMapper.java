package org.example.HW5.mappers;

import org.example.HW5.dto.EmployeesDto;
import org.example.HW5.models.Employees;
import org.mapstruct.factory.Mappers;

public interface EmployeesMapper {

    EmployeesMapper INSTANCE = Mappers.getMapper(EmployeesMapper.class);


    //Get
    EmployeesDto toDeptDto(Employees j);

    //Post
    Employees toModel(EmployeesDto dto);
}
