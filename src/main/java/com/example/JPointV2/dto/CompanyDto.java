package com.example.JPointV2.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CompanyDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String descriptions;
    private String type;
    private String website;
    private Long INN;
    private LocalDate creation;
    private LocalDate update;
}
