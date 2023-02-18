package com.example.JPointV2.service;
import com.example.JPointV2.exception.AllException;
import com.example.JPointV2.model.Company;
import com.example.JPointV2.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company createCompany(@Validated Company _company) {
        return companyRepository.save(_company);
    }


    public void updateCompany(@Validated Company _company, Long _companyId) {
        Company comp = companyRepository
                .findById(_companyId)
                .orElseThrow(() -> new AllException("Компания с ID " + _company.getId() + " не найдена"));

        comp.setId(_company.getId());
        comp.setName(_company.getName());
        comp.setEmail(_company.getEmail());
        comp.setPhoneNumber(_company.getPhoneNumber());
        comp.setDescriptions(_company.getDescriptions());
        comp.setType(_company.getType());
        comp.setWebsite(_company.getWebsite());
        comp.setINN(_company.getINN());
        comp.setCreation(_company.getCreation());
        comp.setUpdate(_company.getUpdate());

        companyRepository.save(comp);
    }

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyId(Long _companyId) {
        return Optional.ofNullable(companyRepository.findById(_companyId)
                .orElseThrow(() -> new AllException("Компании с ID" + _companyId + " не найдено")));
    }

    public List<Company> getCompanyByName(String _name) {
        return companyRepository.findByName(_name);
    }

    public List<Company> startStartingWithNames(String _name) {
        return companyRepository.findByNameIsStartingWith(_name);
    }

    public void deleteId(Long _companyId) {
        Optional<Company> company = companyRepository.findById(_companyId);
        if (company.isPresent()) {
            companyRepository.deleteById(_companyId);
        }
        throw new AllException("Компании с " + _companyId + " не существует");
    }
}
