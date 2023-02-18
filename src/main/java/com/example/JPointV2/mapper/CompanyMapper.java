package com.example.JPointV2.mapper;

import com.example.JPointV2.dto.CompanyDto;
import com.example.JPointV2.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyDto convertCompanyToDto(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .email(company.getEmail())
                .phoneNumber(company.getPhoneNumber())
                .descriptions(company.getDescriptions())
                .type(company.getType())
                .website(company.getWebsite())
                .INN(company.getINN())
                .creation(company.getCreation())
                .update(company.getUpdate())
                .build();

    }

    public Company convertDtoToCompany(CompanyDto companyDto) {
        return Company.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .email(companyDto.getEmail())
                .phoneNumber(companyDto.getPhoneNumber())
                .descriptions(companyDto.getDescriptions())
                .type(companyDto.getType())
                .website(companyDto.getWebsite())
                .INN(companyDto.getINN())
                .creation(companyDto.getCreation())
                .update(companyDto.getUpdate())
                .build();

    }
}
