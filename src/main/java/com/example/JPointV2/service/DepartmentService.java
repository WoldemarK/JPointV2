package com.example.JPointV2.service;
import com.example.JPointV2.exception.AllException;
import com.example.JPointV2.model.Department;
import com.example.JPointV2.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Transactional
    public Department createDepartments(@Validated Department _department) {
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department : departmentList)
            if (department.getName().equalsIgnoreCase(_department.getName()))
                throw new AllException("Создаваемый департамент существует " + department.getName());
        return departmentRepository.save(_department);
    }


    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentId(Long id) {
        return Optional.ofNullable(departmentRepository.findById(id)
                .orElseThrow(() ->
                        new AllException("Запрашиваемая информация по " + id + " не найдена")));
    }

    public List<Department> getDepartmentsName(String _name) {
        return departmentRepository.findByName(_name);
    }

    public List<Department> startStartingWithNames(String _name) {
        return departmentRepository.findByNameIsStartingWith(_name);
    }

    @Transactional
    public void updateDepartments(@Validated Department department, Long departmentId) {
        Department dep = departmentRepository.findById(departmentId).get();
        dep.setName(department.getName());
        dep.setDescription(department.getDescription());
        dep.setUser(department.getUser());
        departmentRepository.save(dep);
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