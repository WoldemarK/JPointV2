package com.example.JPointV2.repository;
import com.example.JPointV2.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByName(String name);

    List<Department> findByNameIsStartingWith(String name);



}
