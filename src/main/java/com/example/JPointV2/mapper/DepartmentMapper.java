package com.example.JPointV2.mapper;

import com.example.JPointV2.dto.DepartmentDto;
import com.example.JPointV2.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public DepartmentDto convertDepartmentToDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .peopleCount(department.getPeopleCount())
                .build();

    }

    public Department convertDtoToDepartment(DepartmentDto departmentDto) {
        return Department.builder()
                .id(departmentDto.getId())
                .name(departmentDto.getName())
                .description(departmentDto.getDescription())
                .peopleCount(departmentDto.getId())
                .build();

    }
}
