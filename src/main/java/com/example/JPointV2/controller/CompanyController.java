package com.example.JPointV2.controller;

import com.example.JPointV2.dto.CompanyDto;
import com.example.JPointV2.model.Company;
import com.example.JPointV2.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        return companyDto == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(companyService.createCompany(companyDto), HttpStatus.CREATED);
    }

    @GetMapping("/company/get/all")
    public ResponseEntity<List<CompanyDto>> getAllCompany() {
        List<CompanyDto> companyList = companyService.getAllCompany();
        if (companyList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<Optional<CompanyDto>> getCompanyId(@PathVariable("companyId") Long companyId) {
        return new ResponseEntity<>(companyService.getCompanyId(companyId), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<CompanyDto>> getNames(String name) {
        return new ResponseEntity<>(companyService.getCompanyByName(name), HttpStatus.OK);
    }

    @GetMapping("/start_name")
    public ResponseEntity<List<CompanyDto>> getStartNames(String name) {
        return new ResponseEntity<>(companyService.startStartingWithNames(name), HttpStatus.OK);
    }

    @PutMapping("/update/{companyId}")
    public ResponseEntity<HttpStatus> update(@PathVariable("companyId") Long companyId,
                                             @RequestBody CompanyDto companyDto) {
        companyService.updateCompany(companyDto, companyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("companyId") Long companyId) {
        companyService.deleteId(companyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}