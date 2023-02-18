package com.example.JPointV2.repository;

import com.example.JPointV2.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    List<Company> findByName(String name);

    List<Company> findByNameIsStartingWith(String name);
}
