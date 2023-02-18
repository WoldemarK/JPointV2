package com.example.JPointV2.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskDto {
    private Long id;
    private String name;
    private String descriptions;
    private Long companyId;
    private Long userId;
    private LocalDate creation;
    private LocalDate update;
}
