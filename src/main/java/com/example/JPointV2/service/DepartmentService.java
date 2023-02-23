package com.example.JPointV2.service;

import com.example.JPointV2.dto.DepartmentDto;
import com.example.JPointV2.exception.AllException;
import com.example.JPointV2.mapper.DepartmentMapper;
import com.example.JPointV2.model.Department;
import com.example.JPointV2.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

//    @Transactional
//    public DepartmentDto createDepartments(@Validated DepartmentDto departmentDto) {
////        List<Department> departmentList = departmentRepository.findAll();
////        for (Department department : departmentList)
////            if (department.getName().equalsIgnoreCase(_department.getName()))
////
////              throw new AllException("Создаваемый департамент существует " + department.getName());
//
//        List<Department> departments = departmentRepository.findAll();
//        return  departments
//                .stream()
//                .map(department -> department.getName().compareToIgnoreCase(departmentDto.getName()))
//                .map(depar->departmentMapper.convertDepartmentToDto(depar))
//                .collect(Collectors.toList());
//
//    }


    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::convertDepartmentToDto)
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDto> getDepartmentId(Long id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::convertDepartmentToDto);
    }

    public List<DepartmentDto> getDepartmentsName(String _name) {
        return departmentRepository.findByName(_name)
                .stream()
                .map(departmentMapper::convertDepartmentToDto)
                .collect(Collectors.toList());
    }

    public List<DepartmentDto> startStartingWithNames(String _name) {
        return departmentRepository.findByNameIsStartingWith(_name)
                .stream()
                .map(departmentMapper::convertDepartmentToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateDepartments(@Validated DepartmentDto departmentDto, Long departmentId) {
        Department dep = departmentRepository.findById(departmentId).get();
        dep.setName(departmentDto.getName());
        dep.setDescription(departmentDto.getDescription());
        departmentRepository.save(dep);

        departmentMapper.convertDepartmentToDto(dep);
    }

    @Transactional
    public void deleteId(@Validated Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            departmentRepository.deleteById(id);
        }
        throw new AllException("Пользователя с " + id + " не существует");
    }
}