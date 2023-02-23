package com.example.JPointV2.service;

import com.example.JPointV2.dto.CompanyDto;
import com.example.JPointV2.exception.AllException;
import com.example.JPointV2.mapper.CompanyMapper;
import com.example.JPointV2.model.Company;
import com.example.JPointV2.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyDto createCompany(@Validated CompanyDto companyDto) {
        Company company = companyMapper.convertDtoToCompany(companyDto);
        return companyMapper.convertCompanyToDto(companyRepository.save(company));
    }


    public void updateCompany(@Validated CompanyDto companyDto, Long _companyId) {
        Company comp = companyRepository
                .findById(_companyId)
                .orElseThrow(() -> new AllException("Компания с ID " + companyDto.getId() + " не найдена"));

        comp.setId(companyDto.getId());
        comp.setName(companyDto.getName());
        comp.setEmail(companyDto.getEmail());
        comp.setPhoneNumber(companyDto.getPhoneNumber());
        comp.setDescriptions(companyDto.getDescriptions());
        comp.setType(companyDto.getType());
        comp.setWebsite(companyDto.getWebsite());
        comp.setINN(companyDto.getINN());
        comp.setCreation(companyDto.getCreation());
        comp.setUpdate(companyDto.getUpdate());

        companyRepository.save(comp);

        companyMapper.convertCompanyToDto(comp);
    }

    public List<CompanyDto> getAllCompany() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::convertCompanyToDto)
                .collect(Collectors.toList());
    }

    public Optional<CompanyDto> getCompanyId(Long _companyId) {
        return Optional.ofNullable(companyRepository.findById(_companyId)
                .map(companyMapper::convertCompanyToDto)
                .orElseThrow(() -> new AllException("Компании с ID" + _companyId + " не найдено")));

    }

    public List<CompanyDto> getCompanyByName(String _name) {
        return companyRepository.findByName(_name)
                .stream()
                .map(companyMapper::convertCompanyToDto)
                .collect(Collectors.toList());
    }

    public List<CompanyDto> startStartingWithNames(String _name) {
        return companyRepository.findByNameIsStartingWith(_name).
                stream()
                .map(companyMapper::convertCompanyToDto)
                .collect(Collectors.toList());
    }

    public void deleteId(Long _companyId) {
        Optional<Company> company = companyRepository.findById(_companyId);
        if (company.isPresent()) {
            companyRepository.deleteById(_companyId);
        }
        throw new AllException("Компании с " + _companyId + " не существует");
    }
}
